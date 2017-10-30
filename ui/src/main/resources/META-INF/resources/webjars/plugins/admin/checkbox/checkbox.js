define(['jquery','icheck', 'utils'], function($) {
    return {
        props: {
            value: null,
            checked: [Array, Boolean]
        },
        template: '<input type="checkbox" :checked="isChecked()">',
        data: function() {
            return {
                innerValue: this.value,
                innerChecked: this.checked
            }
        },
        watch: {
            checked: function(val) {
                this.innerChecked = val;
                if(this.isChecked()) {
                    $(this.$el).iCheck('check');
                } else {
                    $(this.$el).iCheck('uncheck');
                }
            },
            value: function(val) {
                this.innerValue = val;
            },
            innerValue: function(val) {
                this.$emit('input', val);
            }
        },
        methods: {
            init: function() {
                if(this.isChecked()) {
                    $(this.$el).iCheck('check');
                } else {
                    $(this.$el).iCheck('uncheck');
                }
            },
            isChecked: function() {
                if(this.innerChecked instanceof Array) {
                    return $.inArray(this.value, this.innerChecked) >= 0;
                } else if(typeof this.innerChecked === 'boolean') {
                    return this.innerChecked;
                }
            },
            onArrayCheck: function(event) {
                if(!this.innerChecked) {
                    return;
                }
                if(event.target.checked) {
                    if(!this.isChecked()){
                        this.innerChecked.push(this.value);
                    }
                } else if($.inArray(this.value, this.innerChecked) >= 0) {
                    this.innerChecked.splice($.inArray(this.value, this.innerChecked), 1);
                }
            },
            onSingleCheck: function(event) {
                if(event.target.checked) {
                    if(!this.isChecked()){
                        this.innerChecked = true;
                    }
                } else if(this.innerChecked) {
                    this.innerChecked = false;
                }
            }
        },
        model: {
            prop: 'checked',
            event: 'check'
        },
        mounted: function () {
            var self = this;
            $(this.$el).iCheck({
                checkboxClass: 'icheckbox_minimal-blue'
            });
            this.init();
            $(this.$el).on('ifChanged', function(event){
                self.$emit('change', event);
                if(self.innerChecked instanceof Array) {
                    self.onArrayCheck(event);
                } else if(typeof self.innerChecked === 'boolean') {
                    self.onSingleCheck(event);
                }
                self.$emit('check', self.innerChecked);
            });
            $(this.$el).on('ifClicked', function(event){
                self.$emit('click', event);
            });
            this.$emit('mounted', this);
        }
    };
});