function saveData() {
	const tableSize = Number($('#tableSize').val());
	const date = $('#date').val();
	const time = $('#time').val();

	window.localStorage.setItem("tableSize", tableSize);
	window.localStorage.setItem("time", time);
	window.localStorage.setItem("date", date);
}