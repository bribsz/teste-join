import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdutoService } from '../produto.service';
import { Produto } from '../produto.model';

@Component({
  selector: 'app-produto-read-all',
  templateUrl: './produto-read-all.component.html',
  styleUrls: ['./produto-read-all.component.css']
})
export class ProdutoReadAllComponent implements OnInit {

  displayedColumns: string[] = ['id', 'nome', 'produtos', 'acoes'];

  id_cat: String = '';

  produtos: Produto[] = [];

  constructor(
    private service: ProdutoService, 
    private route: ActivatedRoute,
    private router: Router
    ) {

  }

  ngOnInit(): void {
      this.id_cat = this.route.snapshot.paramMap.get('id_cat')!;
      this.findAll()
  }

  findAll(): void {
    this.service.findAllByCategoria(this.id_cat).subscribe((resposta) => {
      this.produtos = resposta;
      console.log(this.produtos);
    });
  }

  navegarParaCriarProduto(): void {
    this.router.navigate([`categorias/${this.id_cat}/produtos/create`]);
  }
}
