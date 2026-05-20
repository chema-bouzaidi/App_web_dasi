async function afficherHeader() {
    let url = './ActionServlet?todo=get-statut';
    let jsonResponse = await fetch(url)
            .then((httpResponse) => {
                if (!httpResponse.ok)
                    throw 'HTTP Error ' + httpResponse.status;
                return httpResponse.json();
            })
            .catch((error) => {
                console.log('[AJAX ERROR]', error);
                return null;
            });

    let nav = document.getElementById("header");
    let statut = jsonResponse ? jsonResponse.statut : null;

    if (statut === "client") {
        nav.innerHTML = `
            <div class="nav-gauche">
                <a href="accueil.html">Accueil</a>
                <a href="listemediums.html">Mediums</a>
                <a href="historique.html">Historique</a>
                <a href="AccueilClient.html">Profil Astral</a>
            </div>
            <div class="nav-droite">
                <a onclick="deconnecter()">Deconnexion</a>
            </div>`;
    } else if (statut === "employe") {
        nav.innerHTML = `
            <div class="nav-gauche">
                <a href="AccueilEmp.html">Accueil</a>
                <a href="MaConsultation.html">Ma Consultation</a>
                <a href="Statistiques.html">Statistiques</a>
                <a href="CarteClients.html">Carte Client</a>
            </div>
            <div class="nav-droite">
                <a onclick="deconnecter()">Deconnexion</a>
            </div>`;
    } else {
        nav.innerHTML = `
            <div class="nav-gauche">
                <a href="accueil.html">Accueil</a>
                <a href="listemediums.html">Mediums</a>
            </div>
            <div class="nav-droite">
                <a href="connexion.html">Connexion</a>
                <a href="Inscription.html">Inscription</a>
            </div>`;
    }
}

async function deconnecter() {
    await fetch('./ActionServlet?todo=deconnecter');
    window.location.href = "accueil.html";
}