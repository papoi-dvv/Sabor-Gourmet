/**
 * API Service - Maneja todas las llamadas REST al backend
 */

const API_BASE_URL = '/api';

class ApiService {
    
    // ==================== AUTH ====================
    
    static async login(nombreUsuario, contrasena) {
        // Enviar como application/x-www-form-urlencoded para que
        // Spring Security pueda leer request.getParameter(...) correctamente
        const params = new URLSearchParams();
        params.append('nombreUsuario', nombreUsuario);
        params.append('contrasena', contrasena);

        const response = await fetch(`${API_BASE_URL}/auth/login`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
            body: params.toString(),
            credentials: 'include',
            redirect: 'manual'
        });
        return response;
    }

    static async getCurrentUser() {
        const response = await fetch(`${API_BASE_URL}/auth/me`, {
            credentials: 'include'
        });
        return response.json();
    }

    static async logout() {
        return fetch(`${API_BASE_URL}/auth/logout`, {
            method: 'POST',
            credentials: 'include'
        });
    }

    // ==================== CLIENTES ====================
    
    static async getClientes() {
        const response = await fetch(`${API_BASE_URL}/clientes`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching clientes');
        return response.json();
    }

    static async getCliente(id) {
        const response = await fetch(`${API_BASE_URL}/clientes/${id}`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching cliente');
        return response.json();
    }

    static async createCliente(cliente) {
        const response = await fetch(`${API_BASE_URL}/clientes`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error creating cliente');
        return response.json();
    }

    static async updateCliente(id, cliente) {
        const response = await fetch(`${API_BASE_URL}/clientes/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error updating cliente');
        return response.json();
    }

    static async deleteCliente(id) {
        const response = await fetch(`${API_BASE_URL}/clientes/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error deleting cliente');
        return response;
    }

    // ==================== MESAS ====================
    
    static async getMesas() {
        const response = await fetch(`${API_BASE_URL}/mesas`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching mesas');
        return response.json();
    }

    static async getMesa(id) {
        const response = await fetch(`${API_BASE_URL}/mesas/${id}`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching mesa');
        return response.json();
    }

    static async createMesa(mesa) {
        const response = await fetch(`${API_BASE_URL}/mesas`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(mesa),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error creating mesa');
        return response.json();
    }

    static async updateMesa(id, mesa) {
        const response = await fetch(`${API_BASE_URL}/mesas/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(mesa),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error updating mesa');
        return response.json();
    }

    static async deleteMesa(id) {
        const response = await fetch(`${API_BASE_URL}/mesas/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error deleting mesa');
        return response;
    }

    static async ocuparMesa(id) {
        const response = await fetch(`${API_BASE_URL}/mesas/${id}/ocupar`, {
            method: 'PUT',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error ocupying mesa');
        return response.json();
    }

    static async liberarMesa(id) {
        const response = await fetch(`${API_BASE_URL}/mesas/${id}/liberar`, {
            method: 'PUT',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error liberating mesa');
        return response.json();
    }

    static async getMesasDisponibles() {
        const response = await fetch(`${API_BASE_URL}/mesas/disponibles`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching mesas disponibles');
        return response.json();
    }

    static async getMesasEstadisticas() {
        const response = await fetch(`${API_BASE_URL}/mesas/dashboard/stats`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching mesas stats');
        return response.json();
    }

    // ==================== PLATOS ====================
    
    static async getPlatos() {
        const response = await fetch(`${API_BASE_URL}/platos`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching platos');
        return response.json();
    }

    static async getPlato(id) {
        const response = await fetch(`${API_BASE_URL}/platos/${id}`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching plato');
        return response.json();
    }

    static async createPlato(plato) {
        const response = await fetch(`${API_BASE_URL}/platos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(plato),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error creating plato');
        return response.json();
    }

    static async updatePlato(id, plato) {
        const response = await fetch(`${API_BASE_URL}/platos/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(plato),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error updating plato');
        return response.json();
    }

    static async deletePlato(id) {
        const response = await fetch(`${API_BASE_URL}/platos/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error deleting plato');
        return response;
    }

    static async getMenu() {
        const response = await fetch(`${API_BASE_URL}/platos/menu`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching menu');
        return response.json();
    }

    // ==================== PEDIDOS ====================
    
    static async getPedidos() {
        const response = await fetch(`${API_BASE_URL}/pedidos`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching pedidos');
        return response.json();
    }

    static async getPedido(id) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching pedido');
        return response.json();
    }

    static async createPedido(pedido) {
        const response = await fetch(`${API_BASE_URL}/pedidos`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pedido),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error creating pedido');
        return response.json();
    }

    static async updatePedido(id, pedido) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(pedido),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error updating pedido');
        return response.json();
    }

    static async deletePedido(id) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}`, {
            method: 'DELETE',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error deleting pedido');
        return response;
    }

    static async cambiarEstadoPedido(id, estado) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}/estado`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ estado }),
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error changing pedido state');
        return response.json();
    }

    static async marcarEnPreparacion(id) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}/en-preparacion`, {
            method: 'PUT',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error marking pedido as en-preparacion');
        return response.json();
    }

    static async marcarServido(id) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}/servido`, {
            method: 'PUT',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error marking pedido as servido');
        return response.json();
    }

    static async cerrarPedido(id) {
        const response = await fetch(`${API_BASE_URL}/pedidos/${id}/cerrar`, {
            method: 'PUT',
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error closing pedido');
        return response.json();
    }

    static async getPanelCocina() {
        const response = await fetch(`${API_BASE_URL}/pedidos/cocina/panel`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching cocina panel');
        return response.json();
    }

    // ==================== ADMIN ====================
    
    static async getAdminEstadisticas() {
        const response = await fetch(`${API_BASE_URL}/admin/dashboard/stats`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching admin stats');
        return response.json();
    }

    static async getBitacora() {
        const response = await fetch(`${API_BASE_URL}/admin/bitacora`, {
            credentials: 'include'
        });
        if (!response.ok) throw new Error('Error fetching bitacora');
        return response.json();
    }
}
