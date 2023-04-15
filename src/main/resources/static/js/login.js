$('#loginForm,#regForm').bootstrapValidator({
    fields: {
        username: {
            validators: {
                notEmpty: {
                    message: 'Username cannot be blank.'
                },
                stringLength: {
                    min: 3,
                    max: 15,
                    message: 'username length should be 4 - 15.'
                }
            }
        },
        password: {
            validators: {
                notEmpty: {
                    message: 'Password cannot be blank.'
                },
                stringLength: {
                    min: 6,
                    max: 15,
                    message: 'password length should be 6 - 15.'
                }
            }
        }
    }
});