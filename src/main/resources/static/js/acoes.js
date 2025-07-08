// Função jQuery que é executada quando o DOM está completamente carregado
$(function() {
    // Seleciona o modal de confirmação de exclusão pelo seu ID
    var confirmationModal = $('#confirmacaoExclusaoModal');
    // Seleciona o formulário dentro do modal
    var deleteForm = confirmationModal.find('form');
    // Obtém a URL base para a ação de exclusão do atributo 'data-url-base' do formulário
    var urlBase = deleteForm.attr('data-url-base');

    // Adiciona um listener de clique a todos os botões com a classe 'js-delete-button'
    $('.js-delete-button').on('click', function() {
        // 'this' refere-se ao botão clicado
        var button = $(this);
        // Obtém o ID do jogo do atributo 'data-id' do botão
        var id = button.attr('data-id');
        // Obtém o título do jogo do atributo 'data-title' do botão
        var title = button.attr('data-title');

        // Atualiza o texto dentro do corpo do modal com o título do jogo a ser excluído
        confirmationModal.find('.modal-body span').html('<strong>' + title + '</strong>');
        // Define a ação do formulário de exclusão, concatenando a URL base com o ID do jogo
        deleteForm.attr('action', urlBase + id);
    });
});
