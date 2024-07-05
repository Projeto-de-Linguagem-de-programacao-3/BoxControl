use mercado;
SELECT
    COUNT(distinct cliente.idCliente) AS numeroClientes,
    COUNT(distinct produto.idProduto) AS numeroProdutos,
    COUNT(distinct venda.idVenda) AS numeroVendas,
	SUM(produto.quantidadeEstoque) AS estoqueTotal,
    SUM(produto.estoqueInicial * produto.precoCompra) as gastoEstoque,
    SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra) as gastoPedidos,
    SUM(produto.estoqueInicial * produto.precoCompra) + SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra) as gastoTotal,
    SUM(venda.valorTotal) as faturamento,
    SUM(venda.valorTotal) - (SUM(produto.estoqueInicial * produto.precoCompra) + SUM(pedidoproduto.quantidade * pedidoproduto.precoCompra)) as lucro
FROM
    cliente,
    produto,
    pedidoproduto,
    venda;