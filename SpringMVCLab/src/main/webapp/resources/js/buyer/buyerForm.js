/**
 * 
 */
 document.addEventListener("DOMContentLoaded", () => {
	const lprodGuSel = window.lprodGu;
	const initVal = lprodGuSel.dataset.initVal;
	const CPATH = document.body.dataset.contextPath;
	axios.get(`${CPATH}/ajax/lprod`)
		.then(resp=> {
			console.log(resp.data);
			const lprodList = resp.data;
			
		})
 })