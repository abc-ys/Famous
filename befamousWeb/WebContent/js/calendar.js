<!--
var weekend = [0,6];
var gNow = new Date();
var ggWinCal;
Calendar.Months = ["一", "二", "三", "四", "五", "六","七", "八", "九", "十", "十一", "十二"];
Calendar.DOMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
Calendar.lDOMonth = [31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
Calendar.get_month = Calendar_get_month;
Calendar.get_daysofmonth = Calendar_get_daysofmonth;
Calendar.calc_month_year = Calendar_calc_month_year;
new Calendar();
function Calendar(p_item, p_WinCal, p_month, p_year) {
  if ((p_month == null) && (p_year == null)) return;
  if (p_WinCal == null) this.gWinCal = ggWinCal;
  else this.gWinCal = p_WinCal;
  if (p_month == null) {
    this.gMonthName = null;
    this.gMonth = null;
    this.gYearly = true;
  } else {
    this.gMonthName = Calendar.get_month(p_month);
    this.gMonth = new Number(p_month);
    this.gYearly = false;
  }
  this.gYear = p_year;
  this.gReturnItem = p_item;
}
function Calendar_print() {	ggWinCal.print(); }
function Calendar_get_month(monthNo) { return Calendar.Months[monthNo]; }
function Calendar_get_daysofmonth(monthNo, p_year) {
  if ((p_year % 4) == 0) {
    if ((p_year % 100) == 0 && (p_year % 400) != 0) return Calendar.DOMonth[monthNo];
	else return Calendar.lDOMonth[monthNo];
  } else { return Calendar.DOMonth[monthNo]; }
}
function Calendar_calc_month_year(p_Month, p_Year, incr) {
  var ret_arr = new Array();
  if (incr == -1) {
    if (p_Month == 0) {
      ret_arr[0] = 11;
      ret_arr[1] = parseInt(p_Year) - 1;
    }  else {
      ret_arr[0] = parseInt(p_Month) - 1;
      ret_arr[1] = parseInt(p_Year);
    }
  } else if (incr == 1) {
    if (p_Month == 11) {
      ret_arr[0] = 0;
      ret_arr[1] = parseInt(p_Year) + 1;
    } else {
      ret_arr[0] = parseInt(p_Month) + 1;
      ret_arr[1] = parseInt(p_Year);
    }
  }
  return ret_arr;
}
Calendar.prototype.getMonthlyCalendarCode = function() {
  var vCode = "";
  var vHeader_Code = "";
  var vData_Code = "";
  vHeader_Code = this.cal_header();
  vData_Code = this.cal_data();
  vCode = vCode + vHeader_Code + vData_Code;
  vCode = vCode + "</TABLE>";
  return vCode;
}
Calendar.prototype.show = function() {
  var vCode = "";
  this.gWinCal.document.open();
  this.wwrite("<HTML><HEAD><TITLE>日曆</TITLE></HEAD><BODY>");
  this.wwrite("<TABLE cellpadding=2 cellspacing=1 BORDER=1 WIDTH='100%'>");
  this.wwriteA("<CAPTION><font size=2 >" + this.gYear + " 年 " + this.gMonthName + " 月</font></CAPTION>");
  var prevMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, -1);
  var prevMM = prevMMYYYY[0];
  var prevYYYY = prevMMYYYY[1];
  var nextMMYYYY = Calendar.calc_month_year(this.gMonth, this.gYear, 1);
  var nextMM = nextMMYYYY[0];
  var nextYYYY = nextMMYYYY[1];
  vCode = this.getMonthlyCalendarCode();
  this.wwrite(vCode);
  this.wwrite("<br><TABLE WIDTH='100%' BORDER=0 CELLSPACING=0 CELLPADDING=0><TH>");
  this.wwrite("<SMALL>[<A HREF=\"javascript:window.opener.Build('" + this.gReturnItem + "', '" + this.gMonth + "', '" + (parseInt(this.gYear)-1) + "', '" + this.gFormat + "');\"><font size=2 color=#72AEE0>YearDown<\/A>]</SMALL></TH><TH>\n");
  this.wwrite("<SMALL>[<A HREF=\"javascript:window.opener.Build('" + this.gReturnItem + "', '" + prevMM + "', '" + prevYYYY + "', '" + this.gFormat + "');\"><font size=2 color=#72AEE0>MonthDown<\/A>]</SMALL></TH><TH>\n");
  this.wwrite("<SMALL>[<A HREF=\"javascript:window.opener.Build('" + this.gReturnItem + "', '" + nextMM + "', '" + nextYYYY + "', '" + this.gFormat + "');\"><font size=2 color=#72AEE0>MonthUp<\/A>]</SMALL></TH><TH>\n");
  this.wwrite("<SMALL>[<A HREF=\"javascript:window.opener.Build('" + this.gReturnItem + "', '" + this.gMonth + "', '" + (parseInt(this.gYear)+1) + "', '" + this.gFormat + "');\"><font size=2 color=#72AEE0>YearUp<\/A>]</SMALL></TH></TABLE>\n");
   
  this.gWinCal.document.close();
}
Calendar.prototype.wwrite = function(wtext) { this.gWinCal.document.writeln(wtext); }
Calendar.prototype.wwriteA = function(wtext) { this.gWinCal.document.write(wtext); }
Calendar.prototype.cal_header = function() {
  var vCode = "";
  vCode = vCode + "<TR BGCOLOR=\"#72AEE0\">";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>日</font></TH>";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>一</font></TH>";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>二</font></TH>";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>三</font></TH>";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>四</font></TH>";
  vCode = vCode + "<TH WIDTH='14%'><font size=2 color=#FFFFFF>五</font></TH>";
  vCode = vCode + "<TH WIDTH='16%'><font size=2 color=#FFFFFF>六</font></TH>";
  vCode = vCode + "</TR>";
  return vCode;
}
Calendar.prototype.cal_data = function() {
  var vDate = new Date();
  vDate.setDate(1);
  vDate.setMonth(this.gMonth);
  vDate.setFullYear(this.gYear);
  var vFirstDay=vDate.getDay();
  var vDay=1;
  var vLastDay=Calendar.get_daysofmonth(this.gMonth, this.gYear);
  var vOnLastDay=0;
  var vCode = "";
  vCode = vCode + "<TR ALIGN=\"CENTER\">\n";
  for (i=0; i<vFirstDay; i++) {
    vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(i) + ">&nbsp;</TD>\n";
  }
  for (j=vFirstDay; j<7; j++) {
    vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j) + ">" + 
	"<A HREF='#' onClick=\"self.opener.document." + this.gReturnItem + ".value='" + 
	this.format_data(vDay) + "'; window.close();\"><font size=2>" + this.format_day(vDay) + "</A></TD>\n";
    vDay=vDay + 1;
  }
  vCode = vCode + "</TR>\n";
  for (k=2; k<7; k++) {
    vCode = vCode + "<TR ALIGN=\"CENTER\">";
    for (j=0; j<7; j++) {
      vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j) + ">" + 
      "<A HREF='#' onClick=\"self.opener.document." + this.gReturnItem + ".value='" + 
      this.format_data(vDay) + "'; window.close();\"><font size=2>" + this.format_day(vDay) + "</A></TD>\n";
      vDay=vDay + 1;
      if (vDay > vLastDay) {
        vOnLastDay = 1;
        break;
      }
    }
    if (j == 6)	vCode = vCode + "</TR>";
    if (vOnLastDay == 1) break;
  }
  for (m=1; m<(7-j); m++) {
    if (this.gYearly) vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j+m) + ">&nbsp;</TD>\n";
	else vCode = vCode + "<TD WIDTH='14%'" + this.write_weekend_string(j+m) + "><FONT size=2 COLOR='#cccccc'>" + m + "</FONT></TD>\n";
  }
  return vCode;
}
Calendar.prototype.format_day = function(vday) {
  var vNowDay = gNow.getDate();
  var vNowMonth = gNow.getMonth();
  var vNowYear = gNow.getFullYear();
  if (vday == vNowDay && this.gMonth == vNowMonth && this.gYear == vNowYear) {
    return ("<FONT size=2 COLOR=\"RED\"><B>" + vday + "</B></FONT>");
  } else { return (vday); }
}
Calendar.prototype.write_weekend_string = function(vday) {
  var i;
  for (i=0; i<weekend.length; i++) {
    if (vday == weekend[i]) return (" BGCOLOR=\"#F2F2F2\"");
  }
  return "";
}
Calendar.prototype.format_data = function(p_day) {
  var vData;
  var vMonth = 1 + this.gMonth;
  vMonth = (vMonth.toString().length < 2) ? "0" + vMonth : vMonth;
  var vY4 = new String(this.gYear);
  var vDD = (p_day.toString().length < 2) ? "0" + p_day : p_day;
  vData = vY4 + "\-" + vMonth + "\-" + vDD;
  return vData;
}
function Build(p_item, p_month, p_year) {
  var p_WinCal = ggWinCal;
  gCal = new Calendar(p_item, p_WinCal, p_month, p_year);
  gCal.show();
}
function show_calendar() {
  p_item = arguments[0];
  if (arguments[1] == null) p_month = new String(gNow.getMonth());
  else p_month = arguments[1];
  if (arguments[2] == "" || arguments[2] == null) p_year = new String(gNow.getFullYear().toString());
  else p_year = arguments[2];
  vWinCal = window.open("", "Calendar", "width=300,height=250,status=no,resizable=no,top=200,left=200");
  vWinCal.opener = self;
  ggWinCal = vWinCal;
  Build(p_item, p_month, p_year);
}
// -->