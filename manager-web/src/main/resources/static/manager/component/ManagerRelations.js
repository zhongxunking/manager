//管理员权限组件
const ManagerRelationsTemplate = `
<div>
    <el-row>
        <el-col>
            <el-form :v-model="queryRelationsForm" :inline="true" size="small">
                <el-form-item>
                    <el-input v-model="queryRelationsForm.managerId" clearable placeholder="管理员id"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-input v-model="queryRelationsForm.targetId" clearable :placeholder="targetName + 'id'"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" @click="queryRelations">查询</el-button>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-plus" @click="addRelationDialogVisible = true">新增</el-button>
                </el-form-item>
            </el-form>
        </el-col>
    </el-row>
    <el-table :data="relations" v-loading="relationsLoading" border stripe>
        <el-table-column prop="managerId" label="管理员">
            <template slot-scope="{ row }">
                {{ toShowingManager(row.manager) }}
            </template>
        </el-table-column>
        <el-table-column prop="targetId" :label="targetName">
            <template slot-scope="{ row }">
                {{ row.target.label }}
            </template>
        </el-table-column>
        <el-table-column label="操作" header-align="center" width="160px">
            <template slot-scope="{ row }">
                <el-row>
                    <el-col style="text-align: center">
                        <el-tooltip content="删除" placement="top" :open-delay="1000" :hide-after="3000">
                            <el-button @click="deleteRelation(row)" type="danger" icon="el-icon-delete" size="small" circle></el-button>
                        </el-tooltip>
                    </el-col>
                </el-row>
            </template>
        </el-table-column>
    </el-table>
    <el-row style="margin-top: 10px">
        <el-col style="text-align: end">
            <el-pagination :total="totalRelations" :current-page.sync="queryRelationsForm.pageNo" :page-size.sync="queryRelationsForm.pageSize" @current-change="queryRelations" layout="total,prev,pager,next" small background></el-pagination>
        </el-col>
    </el-row>
    <el-dialog :visible.sync="addRelationDialogVisible" :before-close="closeAddRelationDialog" title="新增权限" width="40%">
        <el-form ref="addRelationForm" :model="addRelationForm" label-width="30%">
            <el-form-item label="管理员" prop="managerId" :rules="[{required:true, message:'请选择管理员', trigger:'blur'}]">
                <el-select v-model="addRelationForm.managerId" filterable remote :remote-method="queryMatchedManagers" @focus="queryMatchedManagers(addRelationForm.managerId)" clearable placeholder="请选择管理员" style="width: 90%">
                    <el-option v-for="manager in matchedManagers" :value="manager.managerId" :label="toShowingManager(manager)" :key="manager.managerId"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item :label="targetName" prop="targetId" :rules="[{required:true, message:'请选择'+targetName, trigger:'blur'}]">
                <el-select v-model="addRelationForm.targetId" filterable remote :remote-method="doQueryMatchedTargets" @focus="doQueryMatchedTargets(addRelationForm.targetId)" clearable :placeholder="'请选择' + targetName" style="width: 90%">
                    <el-option v-for="target in matchedTargets" :value="target.id" :label="target.label" :key="target.id"></el-option>
                </el-select>
            </el-form-item>
        </el-form>
        <div slot="footer">
            <el-button @click="closeAddRelationDialog">取消</el-button>
            <el-button type="primary" @click="addRelation">提交</el-button>
        </div>
    </el-dialog>
</div>
`;

const ManagerRelations = {
    template: ManagerRelationsTemplate,
    props: ['targetName', 'findTarget', 'queryMatchedTargets'],
    data: function () {
        return {
            queryRelationsForm: {
                pageNo: 1,
                pageSize: 10,
                managerId: null,
                targetId: null
            },
            relationsLoading: false,
            totalRelations: 0,
            relations: [],
            matchedManagers: null,
            matchedTargets: null,
            addRelationDialogVisible: false,
            addRelationForm: {
                managerId: null,
                targetId: null
            }
        };
    },
    created: function () {
        this.queryRelations();
    },
    methods: {
        queryRelations: function () {
            this.relationsLoading = true;
            const theThis = this;
            axios.get(managerRootPath + '/manager/relation/query', {params: this.queryRelationsForm})
                .then(function (result) {
                    theThis.relationsLoading = false;
                    if (!result.success) {
                        Vue.prototype.$message.error(result.message);
                        return;
                    }
                    theThis.totalRelations = result.totalCount;
                    theThis.relations = result.infos;
                    theThis.relations.forEach(function (relation) {
                        // 查找管理员
                        Vue.set(relation, "manager", null);
                        axios.get(managerRootPath + '/manager/manage/findManager', {
                            params: {
                                managerId: relation.managerId
                            }
                        }).then(function (result) {
                            if (!result.success) {
                                Vue.prototype.$message.error(result.message);
                                return;
                            }
                            relation.manager = result.manager;
                        });
                        // 查找目标对象
                        Vue.set(relation, "target", null);
                        theThis.findTarget(relation.targetId, function (target) {
                            relation.target = target;
                        });
                    })
                });
        },
        toShowingManager: function (manager) {
            if (!manager) {
                return '';
            }
            let text = manager.managerId;
            if (manager.name) {
                text += '（' + manager.name + '）';
            }
            return text;
        },
        deleteRelation: function (relation) {
            const theThis = this;
            Vue.prototype.$confirm('确定删除该权限？', '警告', {type: 'warning'})
                .then(function () {
                    axios.post(managerRootPath + '/manager/relation/delete', {
                        managerId: relation.managerId,
                        targetId: relation.targetId
                    }).then(function (result) {
                        if (!result.success) {
                            Vue.prototype.$message.error(result.message);
                            return;
                        }
                        Vue.prototype.$message.success(result.message);
                        theThis.queryRelations();
                    });
                });
        },
        queryMatchedManagers: function (managerId) {
            const theThis = this;
            axios.get(managerRootPath + '/manager/manage/query', {
                params: {
                    pageNo: 1,
                    pageSize: 100,
                    managerId: managerId
                }
            }).then(function (result) {
                if (!result.success) {
                    Vue.prototype.$message.error(result.message);
                    return;
                }
                theThis.matchedManagers = result.infos;
            });
        },
        doQueryMatchedTargets: function (targetId) {
            const theThis = this;
            this.queryMatchedTargets(targetId, function (matchedTargets) {
                theThis.matchedTargets = matchedTargets;
            });
        },
        addRelation: function () {
            const theThis = this;
            this.$refs.addRelationForm.validate(function (valid) {
                if (!valid) {
                    return;
                }
                axios.post(managerRootPath + '/manager/relation/add', theThis.addRelationForm)
                    .then(function (result) {
                        if (!result.success) {
                            Vue.prototype.$message.error(result.message);
                            return;
                        }
                        Vue.prototype.$message.success(result.message);
                        theThis.closeAddRelationDialog();
                        theThis.queryRelations();
                    });
            })
        },
        closeAddRelationDialog: function () {
            this.addRelationDialogVisible = false;
            this.addRelationForm.managerId = null;
            this.addRelationForm.targetId = null;
        }
    }
};