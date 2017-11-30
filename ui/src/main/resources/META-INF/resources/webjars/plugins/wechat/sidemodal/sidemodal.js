define(['jquery', 'utils'], function($, utils){
    return {
        props: {
            isOpen: {
                type: Boolean,
                default: false
            }
        },
        template: '<div class="page sidemodal" :class="{\'js_show slideIn\':isOpen,\'js_show slideOut\':!isOpen && hashchanged}">\n' +
        '            <div class="weui-panel weui-panel_access">\n' +
        '                <div class="weui-panel__bd">\n' +
        '                    <div class="weui-media-box weui-media-box_text">\n' +
        '                        <h4 class="weui-media-box__title"><slot name="header"></slot></h4>\n' +
        '                    </div>\n' +
        '                    <div class="weui-media-box weui-media-box_text">\n' +
        '                       <slot name="body"></slot>\n' +
        '                    </div>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>',
        data: function() {
            return {
                hashchanged: false
            };
        },
        methods: {
            open: function () {
                this.isOpen = true;
            },
            close: function () {
                this.isOpen = false;
                this.hashchanged = true;
            }
        },
        mounted: function () {
            var self = this;
            this.$emit('mounted', this);
            $(window).on('hashchange.sidemodal' + self._uid, function () {
                var isBackward = location.hash.indexOf('#') < 0;
                if(isBackward) {
                    self.close();
                }
            });
        }
    };
});