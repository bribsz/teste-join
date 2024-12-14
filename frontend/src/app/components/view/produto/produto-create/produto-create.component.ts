import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ProdutoService } from '../produto.service';
import { Produto } from '../produto.model';

@Component({
  selector: 'app-produto-create',
  templateUrl: './produto-create.component.html',
  styleUrls: ['./produto-create.component.css']
})

export class ProdutoCreateComponent implements OnInit {

  id_cat: String = '';

  produto: Produto = {
    id: '',
    nome: '',
    preco: 0
  }

  nome = new FormControl('', [Validators.minLength(3)]);
  preco = new FormControl('', [Validators.required]);

  constructor(
    private service: ProdutoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
      this.id_cat = this.route.snapshot.paramMap.get('id_cat')!;
  }

  create(): void {
    this.service.create(this.produto, this.id_cat).subscribe(resposta => {
      this.router.navigate([`categorias/${this.id_cat}/produtos`]);
      this.service.mensagem('Produto criado com sucesso!');
    },
  )}

  cancel(): void {
    this.router.navigate([`categorias/${this.id_cat}/produtos`]);
  }

  getMessage() {
    if(this.nome.invalid){
      return 'O campo Nome deve conter entre 3 e 100 caracteres';
    }

    return false;
  }
}
