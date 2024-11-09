document.addEventListener('DOMContentLoaded', function () {
    const button = document.querySelector('#participate-button');
    if (button) {
        button.addEventListener('click', function () {
            alert('Obrigado por se interessar em participar! Em breve entraremos em contato.');
        });
    }
});
