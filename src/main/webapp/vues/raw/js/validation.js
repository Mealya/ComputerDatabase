$(function() {

	var name = false;
	var introduced = true;
	var discontinued = true;

	var validateString = function(s) {
		return s != "";
	};

	var validateDate = function(s) {
		return !isNaN(Date.parse(s, "dd/MM/yyyy")) || s == "";
	};

	var validateLong = function(s) {
		return !isNaN(s);
	};

	var validateForm = function() {
		if (name && introduced && discontinued) {
			$('#valid').removeClass('disabled');
			$('#valid').removeAttr('disabled');
		} else {
			$('#valid').addClass('disabled');
			$('#valid').attr('disabled', 'disabled');
		}
	}

	$('#computerName').on('input', function() {
		if (!validateString($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success');

			name = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');

			name = true;
		}

		validateForm();
	});

	$('#introduced').on('input', function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success');

			introduced = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');

			introduced = true;
		}

		validateForm();
	});

	$('#discontinued').on('input', function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success');

			discontinued = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');

			discontinued = true;
		}

		validateForm();
	});

});