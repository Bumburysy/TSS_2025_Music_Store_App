function getAlbumList() {
    fetch('/MusicStoreApplication/api/albums')
        .then(response => response.json())
        .then((albums) => {displayAlbumTable(albums);}
    );
}

function displayAlbumTable(data) {
    let out = '<table border="1">';
    out += '<tr><th>ID</th><th>Tytuł</th><th>Autor</th><th>Gatunek</th><th>Ilość</th><th>Cena</th><th>Akcje</th></tr>';
    for (let i = 0; i < data.length; i++) {
        out += '<tr>' +
            '<td>' + data[i].id + '</td>' +
            '<td><input type="text" id="title_' + data[i].id + '" value="' + data[i].title + '"/></td>' +
            '<td><input type="text" id="artist_' + data[i].id + '" value="' + data[i].artist + '"/></td>' +
            '<td><input type="text" id="genre_' + data[i].id + '" value="' + data[i].genre + '"/></td>' +
            '<td><input type="number" id="quantity_' + data[i].id + '" value="' + data[i].quantity + '"/></td>' +
            '<td><input type="number" step="0.01" id="price_' + data[i].id + '" value="' + data[i].price + '"/></td>' +
            '<td>' +
                '<button onclick="updateAlbum(\'' + data[i].id + '\')">Edytuj</button>' +
                '<button onclick="deleteAlbum(\'' + data[i].id + '\')">Usuń</button>' +
            '</td>' +
            '</tr>';
    }
    out += '</table>';
    document.getElementById("albums").innerHTML = out;
}

function createAlbum() {
    fetch('/MusicStoreApplication/api/albums', 
    {
        method: 'POST',
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        },
        body: JSON.stringify({
            title: 'Nowy album',
            artist: 'Nieznany',
            genre: 'Nieznany',
            price: 1.00,
            quantity: 1
        })
    })
    .then(response => {
        if (!response.ok) {
            return response.text().then(text => { throw new Error(text); });
        }
        return response.json();
    })
    .then(data => {
        console.log("Album zapisany:", data);
        getAlbumList();
    })
    .catch(error => console.error('Error:', error));
}

function updateAlbum(id) {
    fetch('/MusicStoreApplication/api/albums/' + id, {
        method: 'PUT',
        body: JSON.stringify({
            id: id,
            title: document.getElementById("title_" + id).value,
            artist: document.getElementById("artist_" + id).value,
            genre: document.getElementById("genre_" + id).value,
            quantity: parseInt(document.getElementById("quantity_" + id).value),
            price: parseFloat(document.getElementById("price_" + id).value)
        }),
        headers: {
            "Content-type": "application/json; charset=UTF-8"
        }
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('Update failed with status ' + response.status);
        }
        if (response.headers.get("content-type")?.includes("application/json")) {
            return response.json();
        }
    })
    .then(data => {
        if (data) {
            console.log('Updated album:', data);
        }
        getAlbumList();
    })
    .catch(error => console.error('Error:', error));
}


function deleteAlbum(id) {
    fetch('/MusicStoreApplication/api/albums/' + id, {
        method: 'DELETE'
    }).then(response => {
        if (!response.ok) {
            throw new Error('Delete failed with status ' + response.status);
        }
    })
    .then(() => getAlbumList())
    .catch(error => console.error('Error:', error));
}

window.addEventListener("load", getAlbumList);
