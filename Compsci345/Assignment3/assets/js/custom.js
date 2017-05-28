function disableTextArea() {
    $('#c1q1,#answer_c1q1').attr("disabled", "disabled");
}

function change_tab(el) {
    caller = $(el);

    caller.parent().find(".tab-controls").removeClass("active");
    caller.addClass('active');

    tab_ref = caller.attr('data-ref');
    tab_group = caller.parent().attr('data-tabref');

    console.log(tab_ref);
    console.log(tab_group);

    $("#" + tab_group + " .active-tab").removeClass("active-tab");
    $("#" + tab_group + " #" + tab_ref).addClass("active-tab");
}

function addResponse(el1, el2) {
    var response = $("#c1q1_response").val();
    $("#c1q1_add").append(response + "<br/>" + "<p></p>");

}
