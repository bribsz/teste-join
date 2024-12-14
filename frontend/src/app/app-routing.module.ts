import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/view/home/home.component';
import { CategoriaReadComponent } from './components/view/categoria/categoria-read/categoria-read.component';
import { CategoriaCreateComponent } from './components/view/categoria/categoria-create/categoria-create.component';
import { CategoriaDeleteComponent } from './components/view/categoria/categoria-delete/categoria-delete.component';
import { CategoriaUpdateComponent } from './components/view/categoria/categoria-update/categoria-update.component';
import { ProdutoReadComponent } from './components/view/produto/produto-read/produto-read.component';
import { ProdutoDeleteComponent } from './components/view/produto/produto-delete/produto-delete.component';
import { ProdutoUpdateComponent } from './components/view/produto/produto-update/produto-update.component';
import { ProdutoCreateComponent } from './components/view/produto/produto-create/produto-create.component';
import { ProdutoReadAllComponent } from './components/view/produto/produto-read-all/produto-read-all.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path: 'categorias',
    component: CategoriaReadComponent
  },
  {
    path: 'categorias/create',
    component: CategoriaCreateComponent
  },
  {
    path: 'categorias/delete/:id',
    component: CategoriaDeleteComponent
  },
  {
    path: 'categorias/update/:id',
    component: CategoriaUpdateComponent
  },
  {
    path: 'categorias/:id_cat/produtos',
    component: ProdutoReadAllComponent
  },
  {
    path: 'categorias/:id_cat/produtos/create',
    component:  ProdutoCreateComponent
  },
  {
    path: 'categorias/:id_cat/produtos/:id/update',
    component:  ProdutoUpdateComponent
  },
  {
    path: 'categorias/:id_cat/produtos/:id/delete',
    component: ProdutoDeleteComponent
  },
  {
    path: 'categorias/:id_cat/produtos/:id/read',
    component:  ProdutoReadComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
