function sendDeleteRequest(url, successMessage, errorMessage) {
	$.ajax({
		url: url,
		type: 'DELETE',
		success: function(data, status, xhr) {
			console.info(successMessage);
			location.reload();
		},
		error: function(jqXhr, textStatus, errorMessage) {
			alert(errorMessage);
		}
	});
}

function clickChooseCourse(blockId) {
	console.info('Choosing block with block id = ' + blockId + ' to choose course for faculty');
	window.location.href='./faculty/schedule/block/' + blockId;
}

function clickChooseLesson(blockId) {
	console.info('Choosing block with block id = ' + blockId + ' to choose lesson for student');
	window.location.href='./student/schedule/block/' + blockId;
}

function selectCourse(courseId, courseName) {
	console.info('Selected  a course [' + courseId + ": " + courseName + ']');
	$('#selected-item').html(courseName);
	$('#course-id').val(courseId);
	$('#course-name').val(courseName);
}


function selectLesson(lessonId, lessonName) {
	console.info('Selected  a lesson [' + lessonId + ": " + lessonName + ']');
	$('#selected-item').html(lessonName);
	$('#lesson-id').val(lessonId);
}

function selectEntry(entryId, entryName) {
	console.info('Selected  an entry [' + entryId + ": " + entryName + ']');
	$('#entry-id').val(entryId);
	$('#selected-entry').html(entryName);
}

function selectTrack(track) {
	console.info('Selected  a track [' + track + ']');
	$('#track').val(track);
	$('#selected-track').html(track);
}

function viewStudents(lessonId) {
	console.info('View students for lesson id ' + lessonId);
	
	window.location.href='./faculty/view-students/lesson/' + lessonId;
}

function deleteEntry(entryId) {
	console.info('Deleting entry with entry id = ' + entryId);
	var url = '../admin/entry/delete/' + entryId;
	var successMessage = 'Successfully deleted entry with entry id ' + entryId;
	var errorMessage = 'Could not delete the entry since it is used in other data as foreign key';
	
	sendDeleteRequest(url, successMessage, errorMessage);
}

function editEntry(entryId) {
	console.info('Editing entry with entry id = ' + entryId);
	window.location.href='../admin/entry/edit/' + entryId;
}

function deleteCourse(courseId) {
	console.info('Deleting course with course id = ' + courseId);

	$.ajax({
		url: '../admin/course/delete/' + courseId,
		type: 'DELETE',
		success: function(data, status, xhr) {
			console.info('Successfully deleted course with course id ' + courseId);
			location.reload();
		},
		error: function(jqXhr, textStatus, errorMessage) {
			alert('Could not delete the course with course id ' + courseId);
		}
	});
}

function editCourse(courseId) {
	console.info('Editing course with courseid = ' + courseId);
	window.location.href='../admin/course/edit/' + courseId;
}

function deleteBlock(blockId) {
	console.info('Deleting block with block id = ' + blockId);

	$.ajax({
		url: '../admin/block/delete/' + blockId,
		type: 'DELETE',
		success: function(data, status, xhr) {
			location.reload();
		},
		error: function(jqXhr, textStatus, errorMessage) {
			alert('Could not delete the block with block id ' + blockId);
		}
	});
}

function editBlock(blockId) {
	console.info('Editing block with blockid = ' + blockId);
	window.location.href='../admin/block/edit/' + blockId;
}
function deleteStudent(studentId) {
	console.info('Deleting student with student id = ' + studentId);

	$.ajax({
		url: '../admin/student/delete/' + studentId,
		type: 'DELETE',
		success: function(data, status, xhr) {
			location.reload();
		},
		error: function(jqXhr, textStatus, errorMessage) {
			alert('Could not delete a student with student id ' + studentId);
		}
	});
}

function editStudent(studentId) {
	console.info('Editing a student with studentid = ' + studentId);
	window.location.href='../admin/student/edit/' + studentId;
}
function deleteFaculty(facultyId) {
	console.info('Deleting faculty with faculty id = ' + facultyId);

	$.ajax({
		url: '../admin/faculty/delete/' + facultyId,
		type: 'DELETE',
		success: function(data, status, xhr) {
			location.reload();
		},
		error: function(jqXhr, textStatus, errorMessage) {
			alert('Could not delete a faculty with faculty id ' + facultyId);
		}
	});
}

function editFaculty(facultyId) {
	console.info('Editing a faculty with facultyid = ' + facultyId);
	window.location.href='../admin/faculty/edit/' + facultyId;
}