var Calendar = FullCalendar.Calendar;

var calendarEl = document.getElementById('calendar');

var calendar = new Calendar(calendarEl, {
  height : '491px' ,
  eventDidMount: function (info) {
    var eventObj = info.event;
    tippy(info.el, {
      content: eventObj.title,//이벤트 디스크립션을 툴팁으로 가져옵니다. 
    });
  },
  headerToolbar: {
    left: 'prev',
    center: 'title',
    right: 'next today'
  },
  events: {}
});

calendar.render();