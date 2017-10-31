define(['jquery'], function($) {
    return {
        props: {
            title: String,
            footer: {
                type: Boolean,
                default: false
            }
        },
        template: '<div class="js_dialog" style="display: none;">\n' +
        '            <div class="weui-mask"></div>\n' +
        '            <div class="weui-dialog">\n' +
        '                <div class="weui-dialog__hd" v-if="title"><strong class="weui-dialog__title">{{title}}</strong></div>\n' +
        '                <div class="weui-dialog__bd"><slot name="body"></slot></div>\n' +
        '                <div class="weui-dialog__ft">\n' +
        '                    <slot name="footer"></slot>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>',
        methods: {
            open: function() {
                this.$emit('open', this);
                $(this.$el).fadeIn(200);
            },
            close: function() {
                $(this.$el).fadeOut(200);
            }
        },
        mounted: function () {
            this.$emit('mounted', this);
        }
    };
});