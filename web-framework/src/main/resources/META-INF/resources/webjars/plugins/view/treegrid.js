define(['jquery', 'utils'], function($, utils) {
    return {
        props: {
            columns: Array,
            domain: String,
            root: Object,
            formData: {
                type: Object,
                default: Object
            },
            queryKey: {
                type: String,
                default: 'name'
            },
            queryParams: {
                type: Object,
                default: function() {
                    return {
                        sort:'sortNumber,updatedDate',
                        order: 'asc,desc'
                    }
                }
            }
        },
        template: '<div class="row">\n' +
        '                <div class="col-md-3">\n' +
        '                    <div class="box">\n' +
        '                        <div class="box-body">\n' +
        '                            <sidebar\n' +
        '                                    :data="sidebar.data"\n' +
        '                                    :root="sidebar.root"\n' +
        '                                    :selected-id="sidebar.selectedId"\n' +
        '                                    :instance="sidebar"\n' +
        '                                    @click-row="sidebarClick"></sidebar>\n' +
        '                        </div>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '                <div class="col-md-9">\n' +
        '                    <crudgrid' +
        '                            :domain="domain"\n' +
        '                            :columns="columns"\n' +
        '                            :form-data="formData"\n' +
        '                            :query-params="queryParams"\n' +
        '                            :instance="crudgrid"\n' +
        '                            @refresh="loadSidebar"\n' +
        '                            @save="loadSidebar"\n' +
        '                            @remove="loadSidebar"\n' +
        '                            @open="modalOpen"\n' +
        '                    >\n' +
        '                        <template slot="form-body" scope="props">\n' +
        '                            <slot name="form-body" :form="props.form"></slot>\n' +
        '                        </template>\n' +
        '                    </crudgrid>\n' +
        '                </div>\n' +
        '            </div>',
        data: function() {
            return {
                crudgrid: {
                    $instance: {},
                    queryParams: this.queryParams,
                    columns: this.columns
                },
                sidebar: {
                    $instance: {},
                    root: this.root,
                    selectedId: null,
                    data: []
                }
            };
        },
        methods: {
            modalOpen: function() {
                var $form = this.crudgrid.$instance.getForm();
                this.$emit('open', $form);
                if(this.sidebar.$instance.getSelectedId()) {
                    $form.parent = {
                        id: this.sidebar.$instance.getSelectedId()
                    }
                } else {
                    $form.parent = {};
                }
            },
            loadSidebar: function(callback) {
                var self = this;
                $.ajax({
                    url: utils.patchUrl('/api/' + this.domain),
                    data: this.crudgrid.queryParams,
                    success: function(data) {
                        self.sidebar.data = data.content;

                        if(callback instanceof Function) {
                            callback.call(self);
                        }
                    }
                });
            },
            sidebarClick: function(row) {
                this.crudgrid.$instance.load({'parent.id': row.id});
            }
        },
        mounted: function() {
            this.loadSidebar(function() {
                this.sidebarClick(this.sidebar.root);
            });
            this.$emit('mounted', this);
        }
    };
});