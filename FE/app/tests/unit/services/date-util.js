/* jshint -W117 */
'use strict';
describe('DateUtils', function () {

    it('should be return the complete name of day Sunday.', function () {
        var date = new Date(1985, 4, 5);
        expect(date.getDayName()).toEqual('Sunday');
    });

    it('should be return the complete name of day Sunday of 1975.', function () {
        var date = new Date(1975, 6, 13);
        expect(date.getDayName()).toEqual('Sunday');
    });

    it('should be return the name of month.', function () {
        var date = new Date(1985, 4, 5);
        expect(date.getMonthName()).toEqual('May');
    });

    it('should be return the number of the day and the name of the month.', function () {
        var date = new Date(2010, 11, 5); // 2010-12-05
        expect(date.getShortDayAndMonth()).toEqual('12/5');
    });

    it('should be return the short name of day Sunday.', function () {
        var date = new Date(1985, 4, 5);
        expect(date.getShortDayName()).toEqual('SUN');
    });

    it('should be return the days amount of day of the week.', function () {
        var date = new Date(1975, 6, 13);
        var arrayTest = date.getDaysOfTheCurrentWeek();
        expect(arrayTest).toBeArrayOfSize(7);
    });

    it('should be return the days of the given week and the month July.', function () {
        var date = new Date(1975, 6, 13);
        var arrayTest = date.getDaysOfTheCurrentWeek();
        expect(JSON.stringify(arrayTest)).toEqual('["MON 7/7","TUE 7/8","WED 7/9","THU 7/10","FRI 7/11","SAT 7/12","SUN 7/13"]');
    });

    it('should be return the days of the given week and the month May.', function () {
        var date = new Date(1985, 4, 5);
        var arrayTest = date.getDaysOfTheCurrentWeek();
        expect(JSON.stringify(arrayTest)).toEqual('["MON 4/29","TUE 4/30","WED 5/1","THU 5/2","FRI 5/3","SAT 5/4","SUN 5/5"]');
    });
    it('should be return the days of the week leap-year from 26.', function () {
        var date = new Date(2012, 1, 26);
        var arrayTest = date.getDaysOfTheCurrentWeek();
        expect(JSON.stringify(arrayTest)).toEqual('["MON 2/20","TUE 2/21","WED 2/22","THU 2/23","FRI 2/24","SAT 2/25","SUN 2/26"]');
    });
    it('should be return the days of the week leap-year from 28.', function () {
        var date = new Date(2012, 1, 28);
        var arrayTest = date.getDaysOfTheCurrentWeek();
        expect(JSON.stringify(arrayTest)).toEqual('["MON 2/27","TUE 2/28","WED 2/29","THU 3/1","FRI 3/2","SAT 3/3","SUN 3/4"]');
    });

});