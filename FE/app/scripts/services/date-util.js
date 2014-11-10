'use strict';
(function () {
    var days = [ 'Sunday' , 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday'];
    var months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];

    Date.prototype.getMonthName = function () {
        return months[this.getMonth()];
    };
    Date.prototype.getDayName = function () {
        return days[this.getDay()];
    };
    Date.prototype.getShortDayName = function () {
        return days[this.getDay()].substring(0, 3).toUpperCase();
    };
    Date.prototype.getShortDayAndMonth = function () {
        return (this.getMonth() + 1) + '/' + this.getDate();
    };
    Date.prototype.getFullDate = function () {
        return this.getShortDayName() + ' ' + this.getShortDayAndMonth();
    };
    Date.prototype.toDay = function () {
        function pad(s) {
            return (s < 10) ? '0' + s : s;
        }

        return [this.getFullYear(), pad(this.getMonth() + 1), pad(this.getDate()), ].join('-');
    };

    Date.prototype.getDaysOfTheCurrentWeek = function () {
        var daysResult = [];
        // CURREN DATE
        var currentYear = this.getFullYear();
        var currentMonth = this.getMonth();
        var date = this.getDate();
        var toDay = { year: currentYear, month: currentMonth, day: date };
        var monday;
        // LAST MONDAY DATE.JS
        if (Date.isLeapYear(currentYear)) {
            var lastMonday = Date.today().set(toDay).last().monday();
            monday = new Date(lastMonday);
        } else {
            var lastMondayNotLeapYear = Date.today().set(toDay).last().monday();
            monday = new Date(moment(lastMondayNotLeapYear));
        }
        daysResult[0] = monday.getFullDate();
        for (var i = 1; i <= 6; i++) {
            // MOMENT.JS
            var dayWeek = new Date(moment(monday.toDay()).add(i, 'days'));
            daysResult[i] = dayWeek.getFullDate();
        }
        return daysResult;
    };

})();