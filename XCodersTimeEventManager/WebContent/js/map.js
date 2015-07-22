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
	scheduler.locale.labels.section_description = 'Description';
	scheduler.locale.labels.section_time = 'Time period';
	scheduler.locale.labels.section_event_location = 'Location';
	/*scheduler.config.lightbox.sections = [
		{"map_to":"text", "name":"description", "type":"textarea", "height":200},
		{"map_to":"auto", "name":"time", "type":"time", "height":72},
		{"map_to":"event_location", "name":"event_location", "type":"textarea", "height":40}
	];*/
	
	scheduler.config.lightbox.sections.push({"map_to":"event_location", "name":"event_location", "type":"textarea", "height":40});
	//scheduler.config.skin = 'terrace';
	scheduler.init('scheduler_here',new Date(2015, 6, 4, 10, 51, 57));
	scheduler.config.prevent_cache = true;
	scheduler.load("events.jsp", "json");
	var dp = new dataProcessor("events.jsp");
	dp.init(scheduler);
	dp.setTransactionMode("POST", false);
	
	