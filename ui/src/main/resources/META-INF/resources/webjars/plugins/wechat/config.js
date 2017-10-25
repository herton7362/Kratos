requirejs.config({
    baseUrl: (window._appConf.ctx || '') + '/webjars/plugins/wechat/',
    paths: {
        'base-jquery': '../jquery/js/jquery-2.1.4.min',
        'jquery': '../jquery/jquery',
        'bootstrap': '../bootstrap/js/bootstrap.min',
        'base-adminlte': '../AdminLTE/js/adminlte.min',
        'adminlte': '../AdminLTE/adminlte',
        'base-vue': '../vue/js/vue',
        'vue':'vue/vue'
    },
    shim: {
        'jquery': ['base-jquery'],
        'vue': ['base-vue'],
        'bootstrap': ['jquery'],
        'base-adminlte': ['bootstrap'],
        'adminlte': [
            'base-adminlte',
            'css!../AdminLTE/css/Ionicons/css/ionicons.min.css',
            'css!../AdminLTE/css/font-awesome/css/font-awesome.min.css',
            'css!../AdminLTE/css/datatables.net-bs/dataTables.bootstrap.min.css'
        ]
    },
    map: {
        '*': {
            'css': '../css.min'
        }
    }
});