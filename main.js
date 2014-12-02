var gradleLogo = document.getElementById("gradle-logo");
var gradleInstall = document.getElementById("gradle-install");
var mavenLogo = document.getElementById("maven-logo");
var mavenInstall = document.getElementById("maven-install");
var installContainer = document.getElementById("install-container");

function forceRender(elem) {
    window.getComputedStyle(elem);
    elem.offsetWidth;
}

function hide(element) {
    element.classList.add("hidden");
}

function show(element) {
    element.classList.remove("removed");
    forceRender(element);
    element.classList.remove("hidden");
}

installContainer.addEventListener("transitionend", function(event) {
    var element = event.target;
    if (event.propertyName === "opacity" && getComputedStyle(element).opacity == "0") {
        element.classList.add("removed");
    }
}, true);

gradleLogo.onclick = function() {
    show(gradleInstall);
    hide(mavenInstall);

    installContainer.classList.add("one-line-medium");
    installContainer.classList.remove("one-line-long");
    installContainer.classList.remove("multiline");

    gradleLogo.classList.remove("disabled");
    mavenLogo.classList.add("disabled");
};

mavenLogo.onclick = function() {
    show(mavenInstall);
    hide(gradleInstall);

    installContainer.classList.add("multiline");
    installContainer.classList.remove("one-line-long");
    installContainer.classList.remove("one-line-medium");

    gradleLogo.classList.add("disabled");
    mavenLogo.classList.remove("disabled");
};
