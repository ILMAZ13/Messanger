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


        function isValidEmailAddress(emailAddress) {
            var pattern = new RegExp(/^(?:[a-zA-Z0-9\._])+@(?:[a-zA-Z0-9\-]+\.)+[a-zA-Z0-9\-]+$/i);
            return pattern.test(emailAddress);
        }

        function checkOther(){
            var email = $('input[name=email]').val()
            if(!isValidEmailAddress(email)){

                //ToDo: show message

                return false
            }
        }

        form.submit(function() {

            addClassEmptyIfNoValue();
            var countOfEmptyFields = form.find('.empty-field').size();

            if(countOfEmptyFields > 0){
                lightEmpty(600)
            } else {
                var noErrors = false;

                //ToDo: check other with regular expressions
                var message = checkOther();
                if(message == '') noErrors = true;

                if(noErrors){
                    $.ajax({
                        url: "/register",
                        type: "POST",
                        data: {
                            "email" : $('input[email=email]').val()

                        }
                    }).done(function (data) {
                        alert(data)
                    });
                }
            }

            return false
        })
    })
});