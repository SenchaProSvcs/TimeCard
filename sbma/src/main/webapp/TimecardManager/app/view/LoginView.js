Ext.define('TimecardManager.view.LoginView', {
    extend: 'Ext.window.Window',

    xst: 'loginView',
    height: 178,
    width: 306,
    layout: {
        type: 'fit'
    },
    title: 'Login to Timecard',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    xst: 'loginForm',
                    layout: {
                        align: 'stretch',
                        type: 'vbox'
                    },
                    bodyPadding: 10,
                    items: [
                        {
                            xtype: 'fieldset',
                            flex: 1,
                            title: 'Enter Account Information',
                            items: [
                                {
                                    xtype: 'textfield',
                                    name: 'userName',
                                    xst: 'userName',
                                    anchor: '100%',
                                    fieldLabel: 'User Name',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'password',
                                    xst: 'password',
                                    anchor: '100%',
                                    inputType: 'password',
                                    fieldLabel: 'Password',
                                    allowBlank: false
                                }
                            ]
                        },
                        {
                            xtype: 'container',
                            layout: {
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'label',
                                    flex: 1
                                },
                                {
                                    xtype: 'button',
                                    xst: 'login',
                                    text: 'Login',
                                    formBind: true
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }

});
