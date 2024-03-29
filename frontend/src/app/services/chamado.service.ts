import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Chamados } from '../components/entities/Chamados';
import { Observable } from 'rxjs';
import { API_CONFIG } from '../config/api.config';

@Injectable({
  providedIn: 'root'
})
export class ChamadoService {

  constructor(
    private http: HttpClient
  ) { }

  findAll(): Observable<Chamados[]> {
    return this.http.get<Chamados[]>(`${API_CONFIG.baseUrl}/chamados`);
  }
  
  findById(id: any): Observable<Chamados> {
    return this.http.get<Chamados>(`${API_CONFIG.baseUrl}/chamados/${id}`);
  }
  
  create(chamado: Chamados): Observable<Chamados> {
    return this.http.post<Chamados>(`${API_CONFIG.baseUrl}/chamados`, chamado);
  }

  update(chamado: Chamados): Observable<Chamados> { 
    return this.http.put<Chamados>(`${API_CONFIG.baseUrl}/chamados/${chamado.id}`, chamado)
   }

}
