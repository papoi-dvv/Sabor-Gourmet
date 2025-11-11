/**
 * UI Utilities - Funciones comunes para la interfaz
 */

class UIUtils {

    /**
     * Muestra una notificación toast
     */
    static showToast(message, type = 'info', duration = 3000) {
        const toastContainer = document.getElementById('toastContainer');
        
        const toastDiv = document.createElement('div');
        toastDiv.className = `toast show alert alert-${type}`;
        toastDiv.role = 'alert';
        toastDiv.innerHTML = `
            <div class="d-flex">
                <div class="flex-grow-1">${message}</div>
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        `;
        
        toastContainer.appendChild(toastDiv);
        
        setTimeout(() => {
            toastDiv.remove();
        }, duration);
    }

    /**
     * Muestra un modal de confirmación
     */
    static showConfirmModal(title, message, onConfirm, onCancel = null) {
        const modal = document.getElementById('confirmModal');
        document.getElementById('confirmModalTitle').textContent = title;
        document.getElementById('confirmModalMessage').textContent = message;
        
        const confirmBtn = document.getElementById('confirmBtn');
        const cancelBtn = document.getElementById('cancelBtn');
        
        const handleConfirm = async () => {
            confirmBtn.disabled = true;
            await onConfirm();
            confirmBtn.disabled = false;
            bootstrap.Modal.getInstance(modal).hide();
        };
        
        const handleCancel = () => {
            if (onCancel) onCancel();
            bootstrap.Modal.getInstance(modal).hide();
        };
        
        confirmBtn.onclick = handleConfirm;
        cancelBtn.onclick = handleCancel;
        
        new bootstrap.Modal(modal).show();
    }

    /**
     * Formatea un número como moneda
     */
    static formatCurrency(value) {
        return new Intl.NumberFormat('es-PE', {
            style: 'currency',
            currency: 'PEN',
            minimumFractionDigits: 2,
            maximumFractionDigits: 2
        }).format(value);
    }

    /**
     * Formatea una fecha
     */
    static formatDate(date) {
        return new Intl.DateTimeFormat('es-PE', {
            year: 'numeric',
            month: '2-digit',
            day: '2-digit',
            hour: '2-digit',
            minute: '2-digit',
            second: '2-digit'
        }).format(new Date(date));
    }

    /**
     * Obtiene el badge CSS para un estado
     */
    static getStatusBadge(estado, tipo = 'estado') {
        const badges = {
            mesa: {
                'DISPONIBLE': 'badge-disponible',
                'OCUPADA': 'badge-ocupada',
                'RESERVADA': 'badge-warning',
                'MANTENIMIENTO': 'badge-secondary'
            },
            pedido: {
                'PENDIENTE': 'badge-pendiente',
                'EN_PREPARACION': 'badge-en-preparacion',
                'SERVIDO': 'badge-servido',
                'CERRADO': 'badge-cerrado'
            },
            cliente: {
                'ACTIVO': 'badge-activo',
                'INACTIVO': 'badge-inactivo'
            },
            plato: {
                'ACTIVO': 'badge-activo',
                'INACTIVO': 'badge-inactivo'
            }
        };
        
        return badges[tipo]?.[estado] || 'badge-secondary';
    }

    /**
     * Muestra un loading spinner
     */
    static showLoading(container) {
        const spinner = document.createElement('div');
        spinner.className = 'text-center p-5';
        spinner.innerHTML = '<div class="spinner"></div>';
        container.innerHTML = '';
        container.appendChild(spinner);
    }

    /**
     * Desactiva los botones del formulario durante el envío
     */
    static disableFormButtons(form, disabled = true) {
        const buttons = form.querySelectorAll('button[type="submit"], button[type="button"]');
        buttons.forEach(btn => {
            btn.disabled = disabled;
            if (disabled) {
                btn.innerHTML = '<span class="spinner"></span> Guardando...';
            }
        });
    }

    /**
     * Valida que un formulario tenga todos los campos requeridos
     */
    static validateForm(form) {
        return form.checkValidity();
    }

    /**
     * Limpia un formulario
     */
    static clearForm(form) {
        form.reset();
        form.classList.remove('was-validated');
    }

    /**
     * Renderiza una tabla a partir de datos
     */
    static renderTable(containerId, columns, data, actions = []) {
        const container = document.getElementById(containerId);
        
        if (data.length === 0) {
            container.innerHTML = '<div class="alert alert-info">No hay datos para mostrar</div>';
            return;
        }

        let html = '<div class="table-responsive"><table class="table table-hover">';
        
        // Headers
        html += '<thead><tr>';
        columns.forEach(col => {
            html += `<th>${col.label}</th>`;
        });
        if (actions.length > 0) html += '<th>Acciones</th>';
        html += '</tr></thead>';
        
        // Body
        html += '<tbody>';
        data.forEach(row => {
            html += '<tr>';
            columns.forEach(col => {
                let value = row[col.field];
                if (col.render) {
                    value = col.render(value, row);
                }
                html += `<td>${value}</td>`;
            });
            
            if (actions.length > 0) {
                html += '<td>';
                actions.forEach(action => {
                    html += `<button class="btn btn-sm btn-${action.type || 'primary'}" 
                             onclick="${action.onclick}('${row.id}')"
                             title="${action.label}">
                             ${action.icon ? `<i class="bi ${action.icon}"></i>` : action.label}
                            </button> `;
                });
                html += '</td>';
            }
            
            html += '</tr>';
        });
        html += '</tbody></table></div>';
        
        container.innerHTML = html;
    }

    /**
     * Muestra un error
     */
    static showError(error) {
        console.error(error);
        const message = error.message || 'Ocurrió un error inesperado';
        this.showToast(message, 'danger');
    }
}

/**
 * Auth Helper - Maneja autenticación
 */
class AuthHelper {
    static currentUser = null;

    static async initialize() {
        try {
            const response = await fetch('/api/auth/me', {
                credentials: 'include'
            });
            if (response.ok) {
                this.currentUser = await response.json();
                console.log('Usuario autenticado:', this.currentUser);
            } else {
                this.currentUser = { authenticated: false };
                console.log('No autenticado (HTTP ' + response.status + ')');
            }
        } catch (error) {
            console.log('Error al verificar autenticación:', error);
            this.currentUser = { authenticated: false };
        }
    }

    static isAuthenticated() {
        return this.currentUser?.authenticated === true;
    }

    static hasRole(role) {
        if (!this.currentUser?.authenticated) return false;
        const roles = this.currentUser.roles || [];
        return roles.includes(`ROLE_${role}`);
    }

    static hasAnyRole(roles) {
        if (!this.currentUser?.authenticated) return false;
        return roles.some(role => this.hasRole(role));
    }

    static getRoles() {
        return this.currentUser?.roles || [];
    }
}

/**
 * Page Helper - Utilidades para páginas específicas
 */
class PageHelper {
    static checkPermission(requiredRoles) {
        if (!AuthHelper.hasAnyRole(requiredRoles)) {
            window.location.href = '/login.html';
            return false;
        }
        return true;
    }

    static hideElementsForUnauthorized(rolesToShow) {
        const elements = document.querySelectorAll('[data-require-role]');
        elements.forEach(el => {
            const requiredRoles = el.dataset.requireRole.split(',');
            if (!AuthHelper.hasAnyRole(requiredRoles)) {
                el.style.display = 'none';
            }
        });
    }
}

// Nota: La inicialización se hace ahora en cada página específica, no aquí globalmente
// Esto evita conflictos y permite mejor control del flujo
