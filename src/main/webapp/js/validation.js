/**
 * Created by apple on 19.10.16.
 */

$(document).ready(function () {
    $(".register-form").each(function () {
        var form = $(this);

        function addClassEmptyIfNoValue() {
            form.find(".input-field").each(function () {
                if($(this).val() != ''){
                    $(this).removeClass("empty-field")
                } else {
                    $(this).addClass("empty-field")
                }
            })
        }
        
        function lightEmpty(interval) {
            var emptyFields = form.find('.empty-field');

            emptyFields.css({
                'border-color' : 'red',
                'border-width' : '1px',
                'box-shadow' : 'inset 0px 0px 5px rgba(100, 0, 0, 70)'
            });

            setTimeout(function () {
                emptyFields.removeAttr('style')
            }, interval)
        }

        form.submit(function() {

            addClassEmptyIfNoValue();
            var countOfEmptyFields = form.find('.empty-field').size();

            if(countOfEmptyFields > 0){
                lightEmpty(600)
            } else {
               form.submit();
            }

            return false
        })
    })
});