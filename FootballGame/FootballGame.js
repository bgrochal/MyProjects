/* 
	Global variables:
	 currentX
	 currentY
	 neighbours
*/

function initializeCanvas() {
	var canvas = document.getElementById('PlayArena');
	var context = canvas.getContext('2d');
	
	currentX = 200;
	currentY = 280;
	neighbours = new Array(117);
	for(var i = 0; i < neighbours.length; i++)
		neighbours[i] = [];
	
	drawPlayArena(context);
	addListeners(canvas, context);
}

function drawPlayArena(context) {
	context.beginPath();
	context.moveTo(40, 80);
	context.lineTo(40, 480);
	context.lineTo(160, 480);
	context.lineTo(160, 520);
	context.lineTo(240, 520);
	context.lineTo(240, 480);
	context.lineTo(360, 480);
	context.lineTo(360, 80);
	context.lineTo(240, 80);
	context.lineTo(240, 40);
	context.lineTo(160, 40);
	context.lineTo(160, 80);
	context.lineTo(40, 80);
	context.lineJoin = 'miter';
	context.lineWidth = 2;
	context.stroke();
	
	for(var y = 80;  y <= 480; y += 40) {
		for(var x = 40; x <= 360; x += 40) {
			context.beginPath();
			context.arc(x, y, 3, 0, 2*Math.PI, true);
			context.fillStyle = 'black';
			context.fill();
			context.stroke();
		}
	}
	
	context.beginPath();
	context.arc(200, 40, 3, 0, 2*Math.PI, true);
	context.fill();
	context.stroke();
	
	context.beginPath();
	context.arc(200, 520, 3, 0, 2*Math.PI, true);
	context.fill();
	context.stroke();
}

function addListeners(canvas, context) {
	canvas.addEventListener('mousedown', mouseDown, false);
	
	function mouseDown(event) {
		var boundingRectangle = canvas.getBoundingClientRect();	/* Getting absolute (x,y) coordinates on Canvas element. */
		var pointedX = event.clientX - boundingRectangle.left;
		var pointedY = event.clientY - boundingRectangle.top;
		
		var shiftX = pointedX%40;
		var shiftY = pointedY%40;
		
		if(shiftX >= 35)
			shiftX -= 40;
		if(shiftY >= 35)
			shiftY -= 40;
		
		if(shiftX >= -5 && shiftY >= -5 && shiftX <= 5 && shiftY <= 5) {	/* Checkinq 5x5 [px] square, not a circle. */
			pointedX -= shiftX;
			pointedY -= shiftY;
			
			
			
			if(pointedX === currentX && pointedY === currentY)
				reportIllegalMove();
			else if(!allowed_point(pointedX, pointedY))
				reportIllegalMove();
			else if(Math.abs(pointedX - currentX) <= 40 && Math.abs(pointedY - currentY) <= 40 && allowed_edge(pointedX, pointedY)) {
				context.beginPath();
				context.moveTo(currentX, currentY);
				context.lineTo(pointedX, pointedY);
				context.lineJoin = 'miter';
				context.lineWidth = 1;
				context.stroke();
				
				currentX = pointedX;
				currentY = pointedY;
			}
			else
				reportIllegalMove();
			
			if(game_win(pointedX, pointedY))
				window.alert("Win!");		/* To implement */
		}
		else {
			reportIllegalMove();
		}
	}
}

function game_win() {
	point_no = 9*(currentY/40 - 1) + (currentX/40 - 1);
	return ((point_no === 4) || (point_no === 112));
}

function allowed_point(coordX, coordY) {
	point_no = 9*(coordY/40 - 1) + (coordX/40 - 1);
	return ((point_no >= 9 && point_no <= 107) || (point_no === 4) || (point_no === 112));
}

function allowed_edge(coordX, coordY) {
	first_point = 9*(currentY/40 - 1) + (currentX/40 - 1);
	second_point = 9*(coordY/40 - 1) + (coordX/40 - 1);
	
	if((first_point%9 === 0 && second_point%9 === 0) || (first_point%9 === 8 && second_point%9 === 8))
		return false;
	
	if(Math.floor(first_point/9) === 1 && Math.floor(second_point/9) === 1) {
		if((Math.min(first_point, second_point) < 12) || (Math.max(first_point, second_point) > 14))
			return false;
	}
	
	if(Math.floor(first_point/9) === 11 && Math.floor(second_point/9) === 11) {
		if((Math.min(first_point, second_point) < 102) || (Math.max(first_point, second_point) > 104))
			return false;
	}
	
	for(var i = 0; i < neighbours[first_point].length; i++) {
		if(neighbours[first_point][i] === second_point)
			return false;
	}
	
	neighbours[first_point].push(second_point);
	neighbours[second_point].push(first_point);
	return true;
}

function reportIllegalMove() {	/* To implement */
	console.log('Illegal!');
}