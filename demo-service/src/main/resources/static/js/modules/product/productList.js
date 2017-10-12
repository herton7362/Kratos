require(['jquery', 'vue', 'messager', 'utils'], function($, Vue, messager, utils) {
    new Vue({
        el: '#content',
        data: {
            crudgrid: {
                queryParams: {
                    name: ''
                },
                columns: [
                    {field:'productCategory.name', title:'分类'},
                    {field:'name', title:'名称'},
                    {field:'remark', title:'备注'},
                    {field:'points', title:'积分'},
                    {field:'price', title:'价格', formatter: function(value) {
                        return utils.formatMoney(value);
                    }}
                ]
            },
            productCategory: {
                data: []
            },
            formData: {
                id: '',
                name: null,
                remark: null,
                points: null,
                price: null,
                productCategory: {}
            }
        },
        methods: {
        },
        mounted: function() {
            var self = this;
            this.crudgrid.$instance.load();
            $.ajax({
                url: utils.patchUrl('/api/productCategory'),
                data: {
                    sort: 'sortNumber,updatedDate',
                    order: 'asc,desc'
                },
                success: function(data) {
                    self.productCategory.data = data.content;
                }
            })
        }
    });
});