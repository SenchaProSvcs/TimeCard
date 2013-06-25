Ext.define('TimecardManager.controller.EventBus', {
    extend: 'Ext.Base',

    mixins: [
        'Ext.util.Observable'
    ],
    singleton: true,

    config: {
    },

    constructor: function(config) {
        this.initConfig(config);
    },

    fireLoginEvent: function(authenticationToken) {
        this.fireEvent('login', authenticationToken);
    },

    addLoginListener: function(fn, scope) {
        this.addListener('login', fn, scope);
    },

    fireLogoutEvent: function() {
        this.fireEvent('logout');
    },

    addLogoutListener: function(fn, scope) {
        this.addListener('logout', fn, scope);
    }

});