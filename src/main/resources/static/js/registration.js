function registrationFormSubmit() {
    let rulesCheckBoxElm = document.getElementById('rulesCheckBox');
    if (!rulesCheckBoxElm.checked){
        alert('you should agree to the rules.')
    } else {
        let form = document.getElementsByTagName('form');
        form[0].submit();
    }
}