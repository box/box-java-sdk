var gradleOption = document.getElementById("gradle-option");
var gradleInstructions = document.getElementById("gradle-instructions");
var mavenOption = document.getElementById("maven-option");
var mavenInstructions = document.getElementById("maven-instructions");
var jarOption = document.getElementById("jar-option");
var jarInstructions = document.getElementById("jar-instructions");
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

function selectGradle() {
    show(gradleInstructions);
    hide(mavenInstructions);
    hide(jarInstructions);

    installContainer.classList.add("one-line");
    installContainer.classList.remove("multiline-long");
    installContainer.classList.remove("multiline");

    gradleOption.classList.remove("inactive");
    mavenOption.classList.add("inactive");
    jarOption.classList.add("inactive");
}

function selectMaven() {
    show(mavenInstructions);
    hide(gradleInstructions);
    hide(jarInstructions);

    installContainer.classList.add("multiline");
    installContainer.classList.remove("multiline-long");
    installContainer.classList.remove("one-line");

    gradleOption.classList.add("inactive");
    mavenOption.classList.remove("inactive");
    jarOption.classList.add("inactive");
}

function selectJar() {
    show(jarInstructions);
    hide(gradleInstructions);
    hide(mavenInstructions);

    installContainer.classList.remove("one-line");
    installContainer.classList.add("multiline-long");
    installContainer.classList.remove("multiline");

    gradleOption.classList.add("inactive");
    mavenOption.classList.add("inactive");
    jarOption.classList.remove("inactive");
}

function navigateHash() {
    if (location.hash === "#maven") {
        selectMaven();
    } else if (location.hash === "#jar") {
        selectJar();
    } else {
        selectGradle();
    }
}

installContainer.addEventListener("transitionend", function(event) {
    var element = event.target;
    if (event.propertyName === "opacity" && getComputedStyle(element).opacity == "0") {
        element.classList.add("removed");
    }
}, true);

gradleOption.onclick = selectGradle;
mavenOption.onclick = selectMaven;
jarOption.onclick = selectJar;
