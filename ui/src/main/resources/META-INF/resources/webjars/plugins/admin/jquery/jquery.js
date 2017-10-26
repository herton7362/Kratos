(function($) {
    require(['messager', 'utils'], function(messager, utils) {
        var ajax = $.ajax;
        $.ajax = function(settings) {
            var fn = {
                    success: function(data, textStatus, jqXHR) {},
                    error: function(XMLHttpRequest, textStatus, errorThrown) {},
                    complete:function(XMLHttpRequest, textStatus) {}
                },
                url = settings.url;
            fn.success = settings.success || function(){};
            fn.error = settings.error || function(){};
            fn.complete = settings.complete || function(){};


            // create settings for compatibility with ajaxSetup
            jQuery.extend(settings, {
                url: url,
                success: function(data, textStatus, jqXHR) {
                    // 跳转到登录页面
                    var  reg = /<title>.*<\/title>/g;
                    if(reg.test(data)) {
                        window.top.location.href = utils.patchUrlPrefixUrl(window._appConf.ctx + window._appConf.loginUrl);
                    }
                    fn.success(data, textStatus, jqXHR);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    if(fn.error(XMLHttpRequest, textStatus, errorThrown) !== false) {
                        if(500=== XMLHttpRequest.status) {
                            messager.bubble('系统内部错误，请联系系统管理员！', 'danger');
                        } else if(406 === XMLHttpRequest.status) {
                            messager.bubble(JSON.parse(XMLHttpRequest.responseText).message, 'warning');
                        } else if(401 === XMLHttpRequest.status) {
                            window.location.href = window._appConf.ctx + window._appConf.loginUrl;
                        } else if(403 === XMLHttpRequest.status) {
                            messager.bubble("没有权限", 'warning');
                        }
                    }
                },
                complete: function(XMLHttpRequest, textStatus) {
                    if(textStatus ==='timeout' || XMLHttpRequest.status === 0){
                        messager.bubble('网络不稳定，请稍后再试！', 'warning');
                    }
                    fn.complete(XMLHttpRequest, textStatus);
                }
            });
            return ajax.apply(this, arguments);
        };
    });
})(jQuery);