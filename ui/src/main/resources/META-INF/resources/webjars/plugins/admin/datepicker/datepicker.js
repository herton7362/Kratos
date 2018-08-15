define(['jquery', 'datepicker-cn', 'utils'], function($) {
    return {
        props: {
            value: [String, Number],
            type: String
        },
        template: '<input type="text" class="form-control" :value="formattedDate">',
        methods: {
            formatter: function (val) {
                if(this.type !== 'date') {
                    return new Date(val).format('yyyy-MM-dd');
                }
                return val;
            }
        },
        watch: {
            value: function(val) {
                if(val) {
                    this.formattedDate = this.formatter(val);
                } else {
                    this.formattedDate = null;
                }
            }
        },
        data: function() {
            return {
                formattedDate: null
            }
        },
        mounted: function () {
            var self = this;
            $(this.$el).datepicker({
                language: 'zh-CN',
                format: 'yyyy-mm-dd',
                autoclose: true
            });
            $(this.$el).on('change', function() {
                if(self.type !== 'date') {
                    self.$emit('input', new Date(this.value).getTime());
                } else {
                    self.$emit('input', this.value);
                }
            });
            this.$emit('mounted', this);
        }
    };
});
