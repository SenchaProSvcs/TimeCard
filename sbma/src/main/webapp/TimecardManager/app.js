Ext.Loader.setConfig({
    disableCaching: false,
    enabled: true,
    paths: {
      'Common' : '/sbma/Common'
    }

});

Ext.application({
    requires: [
         'TimecardManager.controller.EventBus'
    ],
    models: [
        'Common.model.TimeCardItemModel'
    ],
    views: [
        'LoginView'
    ],
    name: 'TimecardManager',
    controllers: [
        'LoginController',
        'MainController'
    ],

    launch: function() {
    }

});

