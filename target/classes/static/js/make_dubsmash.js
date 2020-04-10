
"use strict"

let videoNode = $("#Dubsmash_video");
videoNode.controls = false;


let URL = window.URL || window.webkitURL;
let playSelectedVideo = function () {
    let file = this.files[0];
    let videoNode = $("#Dubsmash_video")[0];
    // let width = $(videoNode.parentNode).css('width');
    let fileURL = URL.createObjectURL(file);
    videoNode.src = fileURL;
    // videoNode.width = width;
}
let inputNodeVideo = document.getElementById("input_video")
inputNodeVideo.addEventListener('change', playSelectedVideo, false)

let URL2 = window.URL || window.webkitURL;
let playSelectedAudio = function () {
    let file = this.files[0]
    let audioNode = document.getElementById("Dubsmash_audio")
    let fileURL2 = URL2.createObjectURL(file)
    audioNode.src = fileURL2
};
let inputNodeAudio = document.getElementById("input_audio")
inputNodeAudio.addEventListener('change', playSelectedAudio, false)

$("#playButton").on('click',onplay = function () {
  $("#Dubsmash_video")[0].play();
  $("#Dubsmash_audio")[0].play();
});

$("#pauseButton").on('click', function () {
    $("#Dubsmash_video")[0].pause();
    $("#Dubsmash_audio")[0].pause();
});

$("#form-1").on('submit',function (event) {
    event.preventDefault();
    let form = $('#form-1')[0];
    let formData = new FormData(form);
    $.ajax({
        type: "POST",
        url: "makeDubsmash",
        data: formData,
        cache: false,
        processData: false,
        contentType: false,
        success: function (data) {
            window.location.replace("");
        },
        error: function (e) {
            alert('error');
        }
    });
});