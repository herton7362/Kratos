requirejs.config({
    baseUrl: (window._appConf.ctx || '') + '/webjars/plugins/',
    paths: {
        'base-jquery': 'jquery/js/jquery-2.1.4.min',
        'jquery': 'jquery/jquery',
        'bootstrap': 'bootstrap/js/bootstrap.min',
        'base-datepicker': 'datepicker/js/bootstrap-datepicker.min',
        'datepicker-cn': 'datepicker/locales/bootstrap-datepicker.zh-CN.min',
        'datepicker': 'datepicker/datepicker',
        'base-adminlte': 'AdminLTE/js/adminlte.min',
        'adminlte': 'AdminLTE/adminlte',
        'base-vue': 'vue/js/vue',
        'vue':'vue/vue',
        'sortablejs': 'vue/js/Sortable.min',
        'vuedraggable': 'vue/js/vuedraggable',
        'utils': 'utils/utils',
        'app': 'utils/app',
        'validator': 'validator/validator',
        'messager': 'messager/js/messager',
        'datagrid': 'datagrid/datagrid',
        'datagrid-cell': 'datagrid/datagridCell',
        'pagination': 'pagination/pagination',
        'sidebar': 'sidebar/js/sidebar',
        'modal': 'modal/modal',
        'crudgrid': 'view/crudgrid',
        'treegrid': 'view/treegrid',
        'icheck': 'iCheck/icheck.min',
        'checkbox': 'checkbox/checkbox',
        'radio': 'checkbox/radio',
        'base-select2': 'select2/js/select2.full',
        'combobox': 'combobox/combobox',
        'combooption': 'combobox/combooption',
        'jstree': 'jstree/jstree',
        'tree': 'tree/tree'
    },
    shim: {
        'vuedraggable':['sortablejs', 'base-vue'],
        'jquery': ['base-jquery'],
        'vue': ['base-vue'],
        'messager': ['jquery', 'vue', 'css!../plugins/messager/css/messager.css'],
        'datagrid': ['css!../plugins/datagrid/css/datagrid.css'],
        'bootstrap': ['jquery'],
        'base-datepicker': ['bootstrap'],
        'datepicker-cn': ['base-datepicker', 'css!../plugins/datepicker/css/bootstrap-datepicker.css'],
        'base-adminlte': ['bootstrap'],
        'adminlte': [
            'base-adminlte',
            'css!../plugins/AdminLTE/css/Ionicons/css/ionicons.min.css',
            'css!../plugins/AdminLTE/css/font-awesome/css/font-awesome.min.css',
            'css!../plugins/AdminLTE/css/datatables.net-bs/dataTables.bootstrap.min.css'
        ],
        'sidebar': ['css!../plugins/sidebar/css/sidebar.css'],
        'pagination': ['css!../plugins/pagination/css/pagination.css'],
        'icheck': ['bootstrap', 'css!../plugins/iCheck/all.css'],
        'base-select2': ['bootstrap'],
        'modal': ['css!../plugins/modal/css/modal.css'],
        'jstree': ['css!../plugins/jstree/themes/default/style.min.css']
    },
    map: {
        '*': {
            'css': 'css.min'
        }
    }
});