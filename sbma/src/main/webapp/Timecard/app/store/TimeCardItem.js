/*
 * File: app/store/TimeCardItem.js
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

Ext.define('Timecard.store.TimeCardItem', {
    extend: 'Ext.data.Store',

    requires: [
        'Timecard.model.TimeCardItem'
    ],

    config: {
        model: 'Timecard.model.TimeCardItem',
        storeId: 'timeCardItem',
        proxy: {
            type: 'ajax',
            reader: {
                type: 'json'
            }
        },
        sorters: {
            property: 'beginDate'
        }
    },

    loadTimeCardItems: function(authenticationToken) {
        var url = '/sbma/rest/timeCardItem?&at=' + authenticationToken;
        this.getProxy().setUrl(url);
        this.load();
    }

});