/**
 * Recupera dados de um objeto.
 * @param {object} objeto Um objeto qualquer que se queira varrer.
 * @param {integer} profundidade Indica se a busca se extenderá a objetos agregados. Valores aceitos: [0..n].
 * @returns {String} Retorna uma string com as propriedades do objeto e seus respectivos valores.
 */
function recuperarDadosObjeto(objeto, profundidade) {
    var dados = '';
    for (var i in objeto) {
        dados += "[" + i + "]: ";
        if(typeof objeto[i] === 'object') {
            if(profundidade > 0) {
                dados += "{" + "\n";
                dados += recuperarDadosObjeto(objeto[i], profundidade - 1);
                dados += "}" + "\n";
            } else {
                dados += "{object}" + "\n";
            }
        } else {
            dados += objeto[i] + "\n";
        }
    }
    return dados;
}
