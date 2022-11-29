import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Chamados } from '../../entities/Chamados';

@Component({
  selector: 'app-chamado-list',
  templateUrl: './chamado-list.component.html',
  styleUrls: ['./chamado-list.component.css']
})
export class ChamadoListComponent implements OnInit {

  ELEMENT_DATA: Chamados[] = [
    {
      id: 1,
      dtAbertura: '01/11/2022',
      dtFechamento: '29/11/2022',
      prioridade: 'ALTA',
      status: 'ANDAMENTO',
      titulo: 'Chamado 1',
      observacao: 'Testando chamado 1',
      tecnico: 1,
      cliente: 7,
      nameTecnico: 'Luiz Bezerra',
      nameCliente: 'Wanessa Ribeiro',
    }
  ];

  displayedColumns: string[] = ['id', 'titulo', 'cliente', 'tecnico', 'dtAbertura', 'prioridade', 'status', 'acoes'];
  dataSource = new MatTableDataSource<Chamados>(this.ELEMENT_DATA);

  constructor() { }

  ngOnInit(): void { }

  @ViewChild(MatPaginator) paginator: MatPaginator;

  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase();
  }

}
