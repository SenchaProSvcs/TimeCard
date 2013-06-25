Ext.define('TimecardManager.controller.LoginController', {
    extend: 'Ext.app.Controller',

    refs: [
        {
            ref: 'password',
            selector: '[xst=password]'
        },
        {
            ref: 'userName',
            selector: '[xst=userName]'
        },
        {
            ref: 'loginView',
            selector: '[xst=loginView]'
        },
        {
            ref: 'loginForm',
            selector: '[xst=loginForm]'
        }
    ],

    init: function(application) {
        this.control({
            "[xst=password]": {
                keyup: this.onPasswordKeyUp
            },
            "[xst=login]": {
                click: this.onLoginRequest
            }
        });
    },
    
    onLaunch: function(application) {
      var loginView = Ext.create('TimecardManager.view.LoginView');
      loginView.show();
	},

    onPasswordKeyUp: function(textfield, e, options) {
	  console.log("onUserNameKeyUp");
    },

    onLoginRequest: function(button, e, options) {
        console.log("onLoginRequest");
        console.log(this.getPassword().getValue());
        var form = this.getLoginForm().getForm();
        if (form.isValid()) {
            this.getLoginView().getEl().mask('Authenticating...');
            var values = form.getValues();
            Ext.Ajax.request({
                method: 'POST',
                url: '/sbma/rest/credential',
                timeout: 60000,
                jsonData: values,
                scope: this,
	            success: this.onLoginCallback,
	            failure: function(response, options) {
		            Ext.Msg.alert("Login", "Cannot communicate with server");
	                this.getLoginView().getEl().unmask();
	            }
            });
        }
    },

    onLoginCallback: function(response, options) {
        this.getLoginView().getEl().unmask();
        var credentialResponse = Ext.decode(response.responseText);
        if (credentialResponse.authenticationToken === null) {
            this.displayError('Login', 'Invalid user name or password');
        } else {
            TimecardManager.controller.EventBus.fireLoginEvent(credentialResponse.authenticationToken);
        }
    }


});
