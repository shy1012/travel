$(function () {
    $.get("header.html",function (data) {
        $("#header").html(data);
        setTimeout(adHide,7000);
    });
    $.get("footer.html",function (data) {
        $("#footer").html(data);
    });
});
function adHide() {
    $("#top_banner").hide("slow");
};
