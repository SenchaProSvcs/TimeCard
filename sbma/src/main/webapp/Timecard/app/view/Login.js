/*
 * File: app/view/Login.js
 *
 * This file was generated by Sencha Architect version 2.1.0.
 * http://www.sencha.com/products/architect/
 *
 * This file requires use of the Sencha Touch 2.0.x library, under independent license.
 * License of Sencha Architect does not include license for Sencha Touch 2.0.x. For more
 * details see http://www.sencha.com/license or contact license@sencha.com.
 *
 * This file will be auto-generated each and everytime you save your project.
 *
 * Do NOT hand edit this file.
 */

Ext.define('Timecard.view.Login', {
    extend: 'Ext.form.Panel',
    alias: 'widget.loginView',

    config: {
        xst: 'loginView',
        items: [
            {
                xtype: 'titlebar',
                docked: 'top',
                title: 'Time Card'
            },
            {
                xtype: 'fieldset',
                xst: 'accountInformation',
                instructions: 'Enter User Name and Password and press Login to continue',
                title: 'Account Information',
                items: [
                    {
                        xtype: 'textfield',
                        itemId: 'userName',
                        label: 'User Name',
                        name: 'userName'
                    },
                    {
                        xtype: 'passwordfield',
                        xst: 'loginPassword',
                        itemId: 'password',
                        label: 'Password',
                        name: 'password'
                    }
                ]
            },
            {
                xtype: 'toolbar',
                docked: 'bottom',
                layout: {
                    pack: 'center',
                    type: 'hbox'
                },
                items: [
                    {
                        xtype: 'button',
                        xst: 'login',
                        iconCls: 'user',
                        iconMask: true,
                        text: 'Login'
                    }
                ]
            }
        ]
    }

});