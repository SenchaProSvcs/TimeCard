/*
 * File: app/view/PunchClock.js
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

Ext.define('Timecard.view.PunchClock', {
    extend: 'Ext.Container',
    alias: 'widget.punchclock',

    config: {
        xst: 'punchClockView',
        layout: {
            type: 'vbox'
        },
        cls: [
            'punch-clock-container'
        ],
        items: [
            {
                xtype: 'titlebar',
                xst: 'clock',
                docked: 'top',
                title: 'Punch Clock',
                items: [
                    {
                        xtype: 'button',
                        xst: 'clockIn',
                        ui: 'confirm',
                        text: 'Clock In'
                    },
                    {
                        xtype: 'button',
                        xst: 'clockOut',
                        align: 'right',
                        ui: 'decline',
                        text: 'Clock Out'
                    }
                ]
            },
            {
                xtype: 'list',
                xst: 'timeCardItemList',
                flex: 1,
                itemTpl: Ext.create('Ext.XTemplate', 
                    '<div style=\'display:table; width:100%;\'>',
                    '    <div style=\'display:table-row;\'>',
                    '        <div style=\'display:table-cell; width:33%; vertical-align: middle;\'><b>{beginDate:this.day}</b><br/>{beginDate:date(\'M j\')}</div>',
                    '        <div style=\'display:table-cell; width:33%; vertical-align: middle;\'>{beginDate:this.time}</div>',
                    '        <div style=\'display:table-cell; width:33%; vertical-align: middle;\'>{[this.getEndTime(values)]}</div>',
                    '    </div>',
                    '</div>',
                    {
                        day: function(date) {
                            return Ext.util.Format.date(date, 'D').toUpperCase();
                        },
                        time: function(date) {
                            var time = Ext.util.Format.date(date, 'h:i a');
                            return time;
                        },
                        getEndTime: function(timeCardItem) {
                            var beginDate = timeCardItem.beginDate;
                            var endDate = timeCardItem.endDate;
                            var endTime = Ext.util.Format.date(endDate, 'h:i a');
                            if (beginDate && endDate) {
                                var elapsedMillis = endDate.getTime() - beginDate.getTime();
                                var elapsedDays = Math.floor(elapsedMillis / (24 * 60 * 60 * 1000));
                                if (elapsedDays > 0) {
                                    endTime = endTime + " + " + elapsedDays;
                                }
                            }
                            return endTime;
                        }
                    }
                ),
                store: 'timeCardItem'
            }
        ]
    },

    initialize: function() {
        var me = this;
        var store = Ext.getStore('timeCardItem');
        store.addAfterListener('load', function(store) {
            me.fireEvent('loadcomplete', store);
        });
    }

});