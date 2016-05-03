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
			$("#errorName").show(50);
			$("#computerNamel").removeClass('glyphicon-ok').addClass('glyphicon-remove');
			name = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$("#errorName").hide(50);
			$(this).parent().first().removeClass('has-error');
			$("#computerNamel").removeClass('glyphicon-remove').addClass('glyphicon-ok');
			name = true;
		}

		validateForm();
	});

	$('#introduced').on('input', function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success'); 
			$(this).parent().first().removeClass('has-warning');
			$("#errorIntro").show(50);
			$("#warnIntro").hide(50);
			$("#introducedl").removeClass('glyphicon-warning-sign');
			$("#introducedl").removeClass('glyphicon-ok').addClass('glyphicon-remove');
			introduced = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');
			$(this).parent().first().removeClass('has-warning');
			$("#errorIntro").hide(50);
			$("#warnIntro").hide(50);
			$("#introducedl").removeClass('glyphicon-warning-sign');
			$("#introducedl").removeClass('glyphicon-remove').addClass('glyphicon-ok');
			introduced = true;
		}

		validateForm();
	});
	$('#introduced').click(function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success'); 
			$(this).parent().first().removeClass('has-warning');
			$("#errorIntro").show(50);
			$("#warnIntro").hide(50);
			$("#introducedl").removeClass('glyphicon-warning-sign');
			$("#introducedl").removeClass('glyphicon-ok').addClass('glyphicon-remove');
			introduced = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');
			$(this).parent().first().removeClass('has-warning');
			$("#errorIntro").hide(50);
			$("#warnIntro").hide(50);
			$("#introducedl").removeClass('glyphicon-warning-sign');
			$("#introducedl").removeClass('glyphicon-remove').addClass('glyphicon-ok');
			introduced = true;
		}

		validateForm();
	});
	$('#discontinued').on('input', function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success');
			$(this).parent().first().removeClass('has-warning');
			$("#warnDisco").hide(50);
			$("#errorDisco").show(50);
			$("#discontinuedl").removeClass('glyphicon-warning-sign');
			$("#discontinuedl").removeClass('glyphicon-ok').addClass('glyphicon-remove');
			discontinued = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');
			$(this).parent().first().removeClass('has-warning');
			$("#warnDisco").hide(50);
			$("#errorDisco").hide(50);
			$("#discontinuedl").removeClass('glyphicon-warning-sign');
			$("#discontinuedl").removeClass('glyphicon-remove').addClass('glyphicon-ok');
			discontinued = true;
		}

		validateForm();
	});
	$('#discontinued').click(function() {
		if (!validateDate($(this).val())) {
			$(this).parent().first().addClass('has-error');
			$(this).parent().first().removeClass('has-success');
			$(this).parent().first().removeClass('has-warning');
			$("#warnDisco").hide(50);
			$("#errorDisco").show(50);
			$("#discontinuedl").removeClass('glyphicon-warning-sign');
			$("#discontinuedl").removeClass('glyphicon-ok').addClass('glyphicon-remove');
			discontinued = false;
		} else {
			$(this).parent().first().addClass('has-success');
			$(this).parent().first().removeClass('has-error');
			$(this).parent().first().removeClass('has-warning');
			$("#warnDisco").hide(50);
			$("#errorDisco").hide(50);
			$("#discontinuedl").removeClass('glyphicon-warning-sign');
			$("#discontinuedl").removeClass('glyphicon-remove').addClass('glyphicon-ok');
			discontinued = true;
		}

		validateForm();
	});
	$('#companyId').click(function() {
		$("#validatorSelect").removeClass('has-warning');
		$("#validatorSelect").addClass('has-success');
	});

});