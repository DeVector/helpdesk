import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { ChamadoService } from 'src/app/services/chamado.service';
import { Chamados } from '../../entities/Chamados';

@Component({
  selector: 'app-chamado-list',
  templateUrl: './chamado-list.component.html',
  styleUrls: ['./chamado-list.component.css']
})
export class ChamadoListComponent implements OnInit {

  ELEMENT_DATA: Chamados[] = [];
  FILTERED_DATA: Chamados[] = [];

  displayedColumns: string[] = ['id', 'titulo', 'cliente', 'tecnico', 'dtAbertura', 'prioridade', 'status', 'acoes'];
  dataSource = new MatTableDataSource<Chamados>(this.ELEMENT_DATA);
  
  @ViewChild(MatPaginator) paginator: MatPaginator;

  constructor(
    private service: ChamadoService
  ) { }

  ngOnInit(): void { 
    this.findAll();
   }

  findAll(): void {
    this.service.findAll().subscribe( resposta => {
      this.ELEMENT_DATA = resposta;
      this.dataSource = new MatTableDataSource<Chamados>(this.ELEMENT_DATA);
      this.dataSource.paginator = this.paginator;
    })
  }

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

  returnStatus(status: any){
    if (status == '0'){
      return 'ABERTO';
    } else if (status == '1') {
      return 'ANDAMENTO';
    } else {
      return 'ENCERRADO';
    }
  }

  returnPrioridade(prioridade: any) {
    if( prioridade == '0'){
      return 'ALTA';
    } else if (prioridade == '1'){
      return 'MEDIA';
    } else {
      return 'BAIXA';
    }
  }

  orderByStatus(status: any): void {
    let list: Chamados[] = [];
    this.ELEMENT_DATA.forEach(element => {
      if(element.status == status) {
        list.push(element);
      }
    });
    this.FILTERED_DATA = list;
    this.dataSource = new MatTableDataSource<Chamados>(this.FILTERED_DATA);
      this.dataSource.paginator = this.paginator;
  }

}
