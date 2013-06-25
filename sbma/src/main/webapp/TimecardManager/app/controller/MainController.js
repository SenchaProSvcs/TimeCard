Ext.define('TimecardManager.controller.MainController', {
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
        
        TimecardManager.controller.EventBus.addLoginListener(this.onLogin, this);
        TimecardManager.controller.EventBus.addLogoutListener(this.onLogout, this);
    },
    
    onLogin: function(authenticationToken) {
	},

	onLogout: function() {
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
