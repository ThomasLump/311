document.getElementById("roleList").querySelectorAll("button[data-target]").forEach(button => {
    button.addEventListener("click", function () {

        document.getElementById("roleList").querySelectorAll("button").forEach(button => {
            button.classList.remove("text-white");
            button.classList.add("text-primary");
            button.classList.remove("bg-primary");
            button.classList.add("bg-transparent");
        })
        this.classList.remove("text-primary");
        this.classList.add("text-white");
        this.classList.remove("bg-transparent");
        this.classList.add("bg-primary");

        let target = this.getAttribute("data-target");
        console.log(target)
        document.querySelectorAll(".content-section").forEach(section => {
            section.style.display = "none";
        });
        document.getElementById(target).style.display = "block";
    });
});

const buttons = document.getElementById("roleList").querySelectorAll("button");
if(buttons.length>0){
    buttons[0].click();
}