$('#form').submit(function() {
		if ($('#tableSize').val() == '') {
			$('#notification').text('Please input your table size');
			return false;
		} else if ($('#date').val() == '') {
			$('#notification').text('Please input your date');
			return false;
		} else if ($('#time').val() == '') {
			$('#notification').text('Please input your time');
			return false;
		} else {
			const tableSize = Number($('#tableSize').val());
            const date = $('#date').val();
            const time = $('#time').val();

            window.localStorage.setItem("tableSize", tableSize);
            window.localStorage.setItem("time", time);
            window.localStorage.setItem("date", date);
	        return true;
		}
})