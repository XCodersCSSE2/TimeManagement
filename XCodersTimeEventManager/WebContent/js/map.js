/**
 *File info : javascript for map view
 *File History
 *----------------------------------------------------
 *date		  index	      name	       info
 *----------------------------------------------------
 *20150715   13208221	 Ishantha	  created.
 *----------------------------------------------------
 */

	scheduler.config.serverLists = {};
	scheduler.locale.labels.map_tab = 'Map';
	name= "map";

	//scheduler.config.skin = 'terrace';
	scheduler.init('scheduler_here',new Date(2015, 6, 4, 10, 51, 57));
	scheduler.config.prevent_cache = true;
	scheduler.load("events.jsp", "json");
	var dp = new dataProcessor("events.jsp");
	dp.init(scheduler);
	dp.setTransactionMode("POST", false);
	
	